package deti.tqs.ua.ServiceTests;

import deti.tqs.ua.repository.TicketRepo;
import deti.tqs.ua.service.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTests {

    @Mock
    private TicketRepo ticketRepo;
    @InjectMocks
    private TicketService ticketService;

    @Test
    @DisplayName("Test finding tickets by ID")
    void testFindTicketsByID() {
        when(ticketRepo.findByTripID(1)).thenReturn(List.of());

        assertThat(ticketService.findTicketsByID(1)).isEmpty();

        verify(ticketRepo, times(1)).findByTripID(1);
    }

    @Test
    @DisplayName("Test finding all tickets")
    void testFindAllTickets() {
        when(ticketRepo.findAll()).thenReturn(List.of());

        assertThat(ticketService.findAllTickets()).isEmpty();

        verify(ticketRepo, times(1)).findAll();
    }
}
