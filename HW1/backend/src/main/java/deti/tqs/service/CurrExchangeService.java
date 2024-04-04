package deti.tqs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    //@Autowired
    public CurrExchangeService () {}


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
    public double exchange(String from, String to) throws Exception {

            try {
                Double rate = Double.parseDouble(cachedRates.get(to).toString());
                logger.info("Cache hit, returning exchange rate");
                return rate;
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Currency not found");
            }

        }


    public List<String> listCurrencies() {
        logger.info("Currencies list requested");
        if (currencies == null) {
            logger.error("Currencies list not found/null");
        }
        return currencies;
    }

}
