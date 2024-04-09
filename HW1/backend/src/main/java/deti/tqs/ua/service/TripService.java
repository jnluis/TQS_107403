package deti.tqs.ua.service;

import deti.tqs.ua.model.Trip;
import deti.tqs.ua.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Service
public class TripService {
    private static final Logger logger = LoggerFactory.getLogger(TripService.class);

    private final TripRepo tripsRepo;
    private final CurrExchangeService currencyExchangeService;

    @Autowired
    public TripService(TripRepo tripsRepo, CurrExchangeService currencyExchangeService) {
        this.tripsRepo = tripsRepo;
        this.currencyExchangeService = currencyExchangeService;
    }

    public boolean tripExists(int tripID) {
        return tripsRepo.existsById(tripID);
    }

    public List<Trip> listTrips(String origin, String destination, String date, String currency) {
        double exchangeRate;
        List<Trip> trips;
        trips = tripsRepo.findByOriginAndDestinationAndDate(origin, destination, date);

        if (currency == null || currency.equals("EUR")) { // EUR is base currency
            return trips;
        }

        try {
            exchangeRate = currencyExchangeService.exchange("EUR", currency);
        } catch (Exception e) {
            logger.error("Error converting currency");
            return Collections.emptyList();
        }

        for (Trip trip : trips) {
            trip.setPrice(trip.getPrice() * exchangeRate); // convert price to new currency in all trips
        }
        logger.info("Trips list requested inside TripService");

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

    public Trip getTrip(int tripID, String currency) {
        Trip trip = tripsRepo.findById(tripID); // Handling null if tripID is not found

        if (trip == null) {
            logger.error("Trip {} not found.", tripID);
            return null; // Return null or throw a custom exception if the trip is not found
        }

        // Return the trip if the currency is null or EUR (base currency), avoiding unnecessary exchange rate fetching
        if (currency == null || currency.equals("EUR")) {
            logger.info("Trip {} requested in currency (EUR).", tripID);
            return trip;
        }
        try {
            double exchangeRate = currencyExchangeService.exchange("EUR", currency);
            trip.setPrice(trip.getPrice() * exchangeRate);
            logger.info("Currency exchange from EUR to {} applied for trip {}.", currency, tripID);
        } catch (Exception e) {
            logger.error("Failed to exchange currency from EUR to {} for trip {}.", currency, tripID, e);
        }

        return trip;
    }
}
