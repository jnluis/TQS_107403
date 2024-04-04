package deti.tqs.repository;

import deti.tqs.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TripRepo extends JpaRepository<Trip, Integer> {
    Trip findById(int id);
    void deleteById(int id);

}
