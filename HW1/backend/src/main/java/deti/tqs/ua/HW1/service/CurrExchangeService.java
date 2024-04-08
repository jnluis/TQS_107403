package deti.tqs.ua.HW1.service;

import deti.tqs.ua.HW1.exception.CurrencyNotFoundException;
import deti.tqs.ua.HW1.exception.ExternalServiceException;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(CurrExchangeService.class);
    public List<String> currencies;
    Map<String, Object> cachedRates = new HashMap<>();
    private long lastCaching = 0;
    private int cacheTTL = 3600 * 100;
    private final String apiKey;

    //@Autowired
    public CurrExchangeService () {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("EXCHANGE_RATE_API_KEY");
    }

    public CurrExchangeService(int TTL) {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("EXCHANGE_RATE_API_KEY");
        cacheTTL = TTL;
        cachedRates = new HashMap<>();
    }

    public boolean storeExchangeRates(Map<String, Object> rates) {
        cachedRates = rates;
        lastCaching = System.currentTimeMillis(); // in ms
        logger.info("Caching exchange rates at " + lastCaching + " for " + cacheTTL + " milliseconds" );
        return true;
    }

    public String RequestAPI(String link) throws Exception {
        URL url = new URL(link);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            // Set HTTP method and timeouts
            con.setRequestMethod("GET");
            con.setConnectTimeout(15000); // 15 seconds connection timeout
            con.setReadTimeout(15000); // 15 seconds read timeout

            //HTTP response code
            int status = con.getResponseCode();
            if (status < HttpURLConnection.HTTP_BAD_REQUEST) {
                // Read response from input stream for successful connections
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    return readResponse(reader);
                }
            } else {
                // Read error message from error stream for failed connections
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getErrorStream()))) {
                    String response = readResponse(reader);
                    throw new Exception("HTTP error code: " + status + ". Server response: " + response);
                }
            }
        } finally {
            con.disconnect();
        }
    }

    private String readResponse(BufferedReader reader) throws IOException {
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        return response.toString();
    }

    public List<String> listCurrencies() throws Exception{
        logger.info("Currencies list requested");
        if (currencies == null)
            exchange("EUR", "USD");
        return currencies;
    }

    public boolean isCacheValid() {
        return !cachedRates.isEmpty() && lastCaching != 0 && System.currentTimeMillis() < lastCaching + cacheTTL;
    }

    public double exchange(String from, String to) throws CurrencyNotFoundException, ExternalServiceException {
        // Check if the cache contains the conversion rates for the 'from' currency and is still valid
        if (isCacheValid() && cachedRates.containsKey(from)) {
            Map<String, Double> rates = (Map<String, Double>) cachedRates.get(from);
            Double rate = rates.get(to);
            if (rate != null) {
                logger.info("Cache hit, returning exchange rate");
                return rate;
            } else {
                throw new CurrencyNotFoundException("Currency not found: " + to);
            }
        } else {
            try {
                logger.info("Cache is not valid, requesting new exchange rates");
                updateExchangeRates(from); // This method updates the cache with new rates
                return exchange(from, to); // Recursive call to retry with updated rates
            } catch (IOException e) {
                throw new ExternalServiceException("Failed to retrieve exchange rates", e);
            }
        }
    }

    private void updateExchangeRates(String from) throws IOException, ExternalServiceException {
        String apiLink = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + from;

        String content;
        try {
            content = RequestAPI(apiLink);
        } catch (Exception e) {
            throw new ExternalServiceException("Error fetching exchange rates from API", e);
        }

        JSONObject obj = new JSONObject(content);
        if (!obj.getString("result").equals("success"))
            throw new ExternalServiceException("API did not return a successful response: " + obj.getString("error-type"));

        JSONObject conversionRates = obj.getJSONObject("conversion_rates");
        Map<String, Double> rates = new HashMap<>();
        for (String key : conversionRates.keySet()) {
            Double value = conversionRates.getDouble(key);
            rates.put(key, value);
        }

        // Assuming 'cachedRates' is a Map<String, Map<String, Double>> to support different base currencies
        cachedRates.put(from, rates);

        // Update 'currencies' if necessary. This might be refactored depending on actual usage.
        currencies = new ArrayList<>(rates.keySet());

        lastCaching = System.currentTimeMillis(); // Update caching time
        logger.info("Updated exchange rates cache for base currency: " + from);
    }
}