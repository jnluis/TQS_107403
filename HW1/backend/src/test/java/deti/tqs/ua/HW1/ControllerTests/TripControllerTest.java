package deti.tqs.ua.HW1.ControllerTests;

import deti.tqs.ua.HW1.controller.TripController;
import deti.tqs.ua.HW1.model.Trip;
import deti.tqs.ua.HW1.service.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = TripController.class,properties="spring.profiles.active=test")
public class TripControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TripService tripService;

    @BeforeEach
    void setUp() throws Exception {

        when(tripService.getDates()).thenReturn(List.of("2024-04-01", "2024-04-02", "2024-04-03"));
        when(tripService.getOrigins()).thenReturn(List.of("Aveiro", "Porto", "Lisboa","Viseu","Braganca"));
        when(tripService.getDestinations()).thenReturn(List.of("Aveiro","Porto","Lisboa","Viseu","Braganca"));
        when(tripService.listTrips("Aveiro", "Viseu", "2024-04-01", "EUR")).thenReturn(List.of(new Trip()));
        when(tripService.getTrip(1, "EUR")).thenReturn(null);
    }

    @Test
    void whenHaveOrigins_thenReturnAll() throws Exception {

        mvc.perform(get("/api/trips/origins"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("Aveiro"));

    }

    @Test
    void whenHaveDestinations_thenReturnAll() throws Exception {

        mvc.perform(get("/api/trips/destinations"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("Aveiro"));

    }

    @Test
    void whenHaveDates_thenReturnAll() throws Exception {

        mvc.perform(get("/api/trips/dates"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("2024-04-01"));

    }

    @Test
    @Disabled
    void whenHaveTrips_thenReturnExistentOne() throws Exception {

        mvc.perform(get("/api/trips/dates"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("2024-04-01"));

    }

    @Test
    void whenHaveNoTrip_findNothing() throws Exception {

        mvc.perform(get("/api/trips/list?origin=Aveiro&destination=Viseu&date=2034-04-01&currency=EUR"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

    }
}
