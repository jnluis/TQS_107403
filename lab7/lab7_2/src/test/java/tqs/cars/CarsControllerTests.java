package tqs.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.cars.boundary.CarController;
import tqs.cars.model.Car;
import tqs.cars.services.CarManagerService;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * WebMvcTest loads a simplified web environment for the tests. Note that the normal
 * auto-discovery of beans (and dependency injection) is limited
 * This strategy deploys the required components to a test-friendly web framework, that can be accessed
 * by injecting a MockMvc reference
 */
@WebMvcTest(CarController.class)
class CarsControllerTests {

    @Autowired
    private MockMvc mvc;    //entry point to the web framework

    // inject required beans as "mockeable" objects
    // note that @AutoWire would result in NoSuchBeanDefinitionException
    @MockBean
    private CarManagerService service;


    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostCars_thenCreateCars( ) throws Exception {
        Car X40 = new Car("Volvo", "X40");

        when( service.save(Mockito.any()) ).thenReturn( X40);

        mvc.perform(
                        post("/api/cars")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.toJson(X40)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.maker", is("Volvo")))
                                .andExpect(jsonPath("$.model", is("X40")));

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car car1 = new Car("Polestar", "2");
        Car car2 = new Car("Fisher", "Forty");
        Car car3 = new Car("Redbull", "RR23");

        List<Car> allCars = Arrays.asList(car1, car2, car3);

        when( service.getAllCars()).thenReturn(allCars);

        mvc.perform(
                        get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(car1.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(car2.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(car3.getMaker())));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void whenGetCarID_thenReturnCar() throws Exception {

        Car ferrari = new Car("Ferrari", "F40");

        //when(CarManagerService.getCarDetails(anyLong())).thenReturn(java.util.Optional.of(ferrari));
        when(service.getCarDetails(anyLong())).thenReturn(java.util.Optional.of(ferrari));
        mvc.perform(get("/api/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maker", is(ferrari.getMaker())))
                .andExpect(jsonPath("$.model", is(ferrari.getModel())));
    }
}