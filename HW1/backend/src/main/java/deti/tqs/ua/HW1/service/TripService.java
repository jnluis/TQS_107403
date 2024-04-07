package deti.tqs.ua.HW1.service;

import deti.tqs.ua.HW1.model.Trip;
import deti.tqs.ua.HW1.repository.TripRepo;
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
            logger.error("Trip" + tripID + " not found.");
            return null; // Return null or throw a custom exception if the trip is not found
        }

        // Return the trip if the currency is null or EUR (base currency), avoiding unnecessary exchange rate fetching
        if (currency == null || currency.equals("EUR")) {
            logger.info("Trip" + tripID + " requested in currency (EUR).");
            return trip;
        }
        try {
            double exchangeRate = currencyExchangeService.exchange("EUR", currency);
            trip.setPrice(trip.getPrice() * exchangeRate);
            logger.info("Currency exchange from EUR to " + currency + " applied for trip" + tripID + ".");
        } catch (Exception e) {
            logger.error("Failed to exchange currency from EUR to " + currency + " for trip" + tripID + ".", e);
        }

        return trip;
    }
}
