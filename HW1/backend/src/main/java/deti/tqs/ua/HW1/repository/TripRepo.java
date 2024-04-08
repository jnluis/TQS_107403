package deti.tqs.ua.HW1.repository;

import deti.tqs.ua.HW1.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TripRepo extends JpaRepository<Trip, Integer> {
    @Query("SELECT t FROM Trip t WHERE (:origin IS NULL OR t.origin = :origin) AND (:destination IS NULL OR t.destination = :destination) AND (:date IS NULL OR t.date = :date)")
    List<Trip> findByOriginAndDestinationAndDate(@Param("origin") String origin, @Param("destination") String destination, @Param("date") String date);

    @Query("SELECT DISTINCT t.date FROM Trip t")
    List<String> findDates();

    @Query("SELECT DISTINCT t.origin FROM Trip t")
    List<String> findOrigins();

    @Query("SELECT DISTINCT t.destination FROM Trip t")
    List<String> findDestinations();

    Trip findById(int id);

    Trip findByPrice(double price);
}