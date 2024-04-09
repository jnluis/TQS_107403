package deti.tqs.ua.HW1.RepositoryTests;

import deti.tqs.ua.HW1.model.TicketDetails;
import deti.tqs.ua.HW1.repository.TicketRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TicketRepoTests {
    @Autowired
    private TicketRepo ticketRepo;

    @Test
    public void testFindByTripID_returnsListOfTickets() {
        TicketDetails ticket = new TicketDetails();
        ticket.setPrice("15");
        ticket.setTripID(1);
        ticket.setCity("Anadia");
        ticketRepo.save(ticket);

        TicketDetails found = ticketRepo.findById(ticket.getId());

        assertEquals(ticket.getId(), found.getId());
        assertThat(found).isEqualTo(ticket);
    }

    @Test
    public void testFindById_nonExistingId_returnsNull() {
        String nonExistingId = "999";

        TicketDetails actualTicketDetails = ticketRepo.findById(nonExistingId);

        assertNull(actualTicketDetails);
    }

    @Test
    public void testFindByTripID_nonExistingTripID_returnsEmptyList() {
        int nonExistingTripID = 20;

        List<TicketDetails> actualTickets = ticketRepo.findByTripID(nonExistingTripID);
        assertTrue(actualTickets.isEmpty());
    }

    @Test
    public void testDeleteByTripID_existingTripID_deletesTickets() {
        // Given there are tickets in the database with a specific trip ID
        int existingTripID = 5;
        TicketDetails ticket1 = new TicketDetails();
        ticket1.setPrice("15");
        ticket1.setTripID(existingTripID);
        ticket1.setCity("Anadia");
        ticketRepo.save(ticket1);

        TicketDetails ticket2 = new TicketDetails();
        ticket2.setPrice("15");
        ticket2.setTripID(existingTripID);
        ticket2.setCity("Vagos");
        ticketRepo.save(ticket2);

        // When the deleteByTripID method is called with the existing trip ID
        ticketRepo.deleteByTripID(existingTripID);

        List<TicketDetails> remainingTickets = ticketRepo.findAll();
        List<TicketDetails> found = ticketRepo.findByTripID(ticket1.getTripID());
        assertEquals(0, remainingTickets.stream().filter(t -> t.getTripID() == existingTripID).count());
        assertThat(found).isEmpty();
    }

    @Test
    public void testDeleteByTripID_nonExistingTripID_doesNothing() {
        // Given there are no tickets in the database with a specific trip ID
        int nonExistingTripID = 25;

        // When the deleteByTripID method is called with the non-existing trip ID
        ticketRepo.deleteByTripID(nonExistingTripID);
    }
}
