package deti.tqs.ua.ControllerTests;

import deti.tqs.ua.model.TicketDetails;
import deti.tqs.ua.model.Trip;
import deti.tqs.ua.repository.TicketRepo;
import deti.tqs.ua.repository.TripRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=test")
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // needed to put this becuse CLI was saying that setup() needed to be static
class TicketControllerTestIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private TripRepo tripRepo;

    private final TicketDetails ticket = new TicketDetails();
    private Trip trip = new Trip();

    @BeforeAll
    void setup() {
        trip.setBusNumber("1");
        trip.setDestination("Aveiro");
        trip.setOrigin("Viseu");
        trip.setDate("2024-04-01");
        trip.setPrice(3.5);
        trip.setTime("10:00");

        trip = tripRepo.saveAndFlush(trip);

        ticket.setTripID(trip.getId());
        ticket.setFirstName("Americo");
        ticket.setLastName("Aguiar");
        ticket.setPrice("3.5");
        ticket.setEmail("americozinho@gmail.com");
        ticketRepo.saveAndFlush(ticket);
    }

    @Test
    void whenPostTicketReserve_thenReturn200() {
        ResponseEntity<TicketDetails> response = restTemplate.postForEntity("/api/ticket/reserve", ticket, TicketDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).getEmail()).isEqualTo("americozinho@gmail.com");
    }

    @Test
    void whenPostInvalidEmail_thenGiveError() {
        TicketDetails ticket = new TicketDetails();
        ticket.setTripID(trip.getId());
        ticket.setFirstName("Americo");
        ticket.setLastName("Aguiar");
        ticket.setEmail("Americoooo");
        ticket.setPrice("15");

        ResponseEntity<TicketDetails> response = restTemplate.postForEntity("/api/ticket/reserve", ticket, TicketDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void whenPostInvalidTripID_thenGiveError() {
        TicketDetails ticket = new TicketDetails();
        ticket.setTripID(40);
        ticket.setFirstName("Americo");
        ticket.setLastName("Aguiar");
        ticket.setEmail("americozinho@gmail.com");
        ticket.setPrice("15");

        ResponseEntity<TicketDetails> response = restTemplate.postForEntity("/api/ticket/reserve", ticket, TicketDetails.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}
