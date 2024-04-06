package deti.tqs.ua.HW1.service;

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
    private String apiKey;

    //@Autowired
    public CurrExchangeService () {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("EXCHANGE_RATE_API_KEY");
    }

    public CurrExchangeService(int TTL) {
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

    public List<String> listCurrencies() {
        logger.info("Currencies list requested");
        if (currencies == null) {
            logger.error("Currencies list not found/null");
        }
        return currencies;
    }

    public boolean isCacheValid() {
        return !cachedRates.isEmpty() && lastCaching != 0 && System.currentTimeMillis() < lastCaching + cacheTTL;
    }

    public double exchange(String from, String to) throws Exception {

        if (isCacheValid()) {

            Double rate = Double.parseDouble(cachedRates.get(to).toString());
            logger.info("Cache hit, returning exchange rate");
            return rate;

        } else {
            logger.info("Cache is not valid, redoing exchange rates request");
        }

        String api_link = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + from;

        String content = RequestAPI(api_link);

        JSONObject obj = new JSONObject(content.toString());

        storeExchangeRates(obj.getJSONObject("conversion_rates").toMap());

        currencies = (List<String>) obj.getJSONObject("conversion_rates").keySet();

        double rate;

        try {
            rate = obj.getJSONObject("conversion_rates").getDouble(to);
        } catch (Exception e) {
            throw new Exception("Currency not found");
        }

        return rate;

    }
}