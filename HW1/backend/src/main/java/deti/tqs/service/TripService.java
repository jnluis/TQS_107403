package deti.tqs.service;


import deti.tqs.model.Trip;
import deti.tqs.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TripService {
    private static final Logger logger = LoggerFactory.getLogger(TripService.class);

    @Autowired
    private TripRepo tripsRepository;

    @Autowired
    CurrExchangeService currencyExchangeService;

    public boolean tripExists(int tripID) {
        return tripsRepository.existsById(tripID);
    }

    public Trip getTrip(int tripID, String currency) {

        Trip trip = tripsRepository.findById(tripID);

        if (currency == null || currency.equals("EUR")) { // EUR is base currency
            return trip;
        }

        double exchange_rate = 1.0;

        try {
            System.out.println("Exchanging currency from EUR to " + currency);
            exchange_rate = currencyExchangeService.exchange("EUR", currency);
        } catch (Exception e) {
            logger.error("Currency exchange failed");
        }

        trip.setPrice(trip.getPrice() * exchange_rate);
        logger.info("Trip with id " + tripID + " requested" + " in currency " + currency);

        return trip;
    }

}
