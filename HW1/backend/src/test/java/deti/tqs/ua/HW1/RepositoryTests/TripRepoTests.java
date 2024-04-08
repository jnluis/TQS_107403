package deti.tqs.ua.HW1.RepositoryTests;

import deti.tqs.ua.HW1.model.Trip;
import deti.tqs.ua.HW1.repository.TripRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Disabled
public class TripRepoTests {
    @Autowired
    private TripRepo tripRepo;

    @Test
    void whenSaved_thenFindById() {
        Trip trip = new Trip();
        trip.setBusNumber("10");
        trip.setOrigin("Aveiro");
        trip.setDestination("Viseu");
        trip.setDate("2024-04-01");
        trip.setTime("22:00");
        trip.setPrice(3.50);

        tripRepo.save(trip);

        Trip found = tripRepo.findById(trip.getId());

        assertEquals(trip.getId(), found.getId());
        assertThat(found).isEqualTo(trip);
    }

    @Test
    void whenSaved_thenFindByPrice() {
        Trip trip = new Trip();
        trip.setBusNumber("10");
        trip.setOrigin("Aveiro");
        trip.setDestination("Viseu");
        trip.setDate("2024-04-01");
        trip.setTime("22:00");
        trip.setPrice(3.50);

        tripRepo.save(trip);

        Trip found = tripRepo.findByPrice(trip.getPrice());

        assertEquals(trip.getId(), found.getId());
        assertThat(found).isEqualTo(trip);
    }

    @Test
    void whenDelete_thenNotFound() {
        Trip trip = new Trip();
        trip.setBusNumber("10");
        trip.setOrigin("Aveiro");
        trip.setDestination("Viseu");
        trip.setDate("2024-04-01");
        trip.setTime("22:00");
        trip.setPrice(3.50);

        tripRepo.save(trip);
        tripRepo.delete(trip);

        assertThat(tripRepo.findById(trip.getId())).isNull();
    }

    @Test
    void whenDeleteAll_thenEmpty() {
        Trip trip = new Trip();
        trip.setBusNumber("10");
        trip.setOrigin("Aveiro");
        trip.setDestination("Viseu");
        trip.setDate("2024-04-01");
        trip.setTime("22:00");
        trip.setPrice(3.50);

        tripRepo.save(trip);
        tripRepo.deleteAll();

        assertThat(tripRepo.findAll()).isEmpty();
    }

}
