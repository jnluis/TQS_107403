package deti.tqs.service;


import deti.tqs.model.Trip;
import deti.tqs.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {
    private static final Logger logger = LoggerFactory.getLogger(TripService.class);

    @Autowired
    private TripRepo tripsRepo;

    @Autowired
    CurrExchangeService currencyExchangeService;

    public boolean tripExists(int tripID) {
        return tripsRepo.existsById(tripID);
    }

    public List<Trip> listTrips(String origin, String destination, String date, String currency) {
        double exchange_rate;
        List<Trip> trips;
        trips = tripsRepo.findByOriginAndDestinationAndDate(origin, destination, date);

        if (currency == null || currency.equals("EUR")) { // EUR is base currency
            return trips;
        }

        try {
            exchange_rate = currencyExchangeService.exchange("EUR", currency);
        } catch (Exception e) {
            logger.error("Error converting currency");
            return null;
        }

        for (Trip trip : trips) {
            trip.setPrice(trip.getPrice() * exchange_rate); // convert price to new currency in all trips
        }
        logger.info("Trips list requested ");

        return trips;
    }

    public List<String> getDates() {
        return tripsRepo.findDates();
    }

    public List<String> getOrigins() {
        return tripsRepo.findOrigins();
    }

    public List<String> getDestinations() {
        return tripsRepo.findDestinations();
    }

}
