package deti.tqs.ua.ServiceTests;

import deti.tqs.ua.model.Trip;
import deti.tqs.ua.repository.TripRepo;
import deti.tqs.ua.service.CurrExchangeService;
import deti.tqs.ua.service.TripService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TripServiceTests{
    @Mock
    private TripRepo tripRepo;
    @Mock
    private CurrExchangeService currExchangeService;
    @InjectMocks
    private TripService tripService;

    @Test
    @DisplayName("Test getting all the origins available in the database")
    void testGetOrigins() {
        when(tripRepo.findOrigins()).thenReturn(Arrays.asList("Aveiro", "Viseu", "Lisboa"));

        assertThat(tripService.getOrigins())
                .contains("Aveiro", "Viseu", "Lisboa")
                .hasSize(3);

        verify(tripRepo, times(1)).findOrigins();
    }

    @Test
    @DisplayName("Test getting all the destinations available in the database")
    void testGetDestinations() {
        when(tripRepo.findDestinations()).thenReturn(Arrays.asList("Aveiro", "Viseu", "Lisboa"));

        assertThat(tripService.getDestinations())
                .contains("Aveiro", "Viseu", "Lisboa")
                .hasSize(3);

        verify(tripRepo, times(1)).findDestinations();
    }

    @Test
    @DisplayName("Test getting all trips with a specific origin, destination and date")
    void testGetTrips() {
        when(tripRepo.findByOriginAndDestinationAndDate("Aveiro", "Viseu", "2024-04-01"))
                .thenReturn(Arrays.asList(new Trip(), new Trip()));

        assertThat(tripService.listTrips("Aveiro", "Viseu", "2024-04-01", "EUR"))
                .hasSize(2);

        verify(tripRepo, times(1)).findByOriginAndDestinationAndDate("Aveiro", "Viseu", "2024-04-01");
    }

    @Test
    @DisplayName("Test getting a specific trip by id but no results")
    void testGetTripsWithoutResults() throws Exception {
        when(tripRepo.findById(1)).thenReturn(null);

        assertThat(tripService.getTrip(1, "EUR")).isNull();

        verify(tripRepo, times(1)).findById(1);
    }

    @Test
    @DisplayName("Test getting all trips")
    void testListTrips() {

        when(tripRepo.findAll()).thenReturn(Arrays.asList(new Trip(), new Trip()));

        assertThat(tripService.listAllTrips())
                .hasSize(2);

        verify(tripRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("Test getting a specific trip with currency exchange")
    void testGetATripWithExchange() throws Exception {
        Trip trip = new Trip();
        trip.setPrice(10.0);
        when(tripRepo.findById(1)).thenReturn(trip);
        // Mock the currency exchange to return a value greater than 1
        when(currExchangeService.exchange("EUR", "USD")).thenReturn(1.2);

        assertThat(tripService.getTrip(1, "USD").getPrice()).isEqualTo(12.0);

        verify(tripRepo, times(1)).findById(1);
    }
}

