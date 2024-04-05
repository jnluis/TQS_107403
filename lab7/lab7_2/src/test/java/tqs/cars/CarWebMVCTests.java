package tqs.cars;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
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



@WebMvcTest(CarController.class)
public class CarWebMVCTests {
    @MockBean
    CarManagerService carManagerService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200() {
        Car car = new Car("Audi", "A3");
        when(carManagerService.save(Mockito.any())).thenReturn(car);

        RestAssuredMockMvc.when()
                .get("/api/cars")
                .then()
                .statusCode(200);
    }

    @Test
    public void whenPostCars_CreateCars_thenStatus200(){
        Car car = new Car("Volvo", "X40");
        when(carManagerService.save(Mockito.any())).thenReturn(car);

        RestAssuredMockMvc.given()
                .contentType("application/json")
                .body(car)
                .when()
                .post("/api/cars")
                .then()
                .statusCode(201);
    }

    @Test
    public void givenManyCars_whenGetCars_thenReturnJsonArray_thenStatus200(){
        Car car = new Car("Volvo", "X40");
        Car car2 = new Car("Fisher", "Forty");

        List<Car> allCars = Arrays.asList(car, car2);
        when( carManagerService.getAllCars()).thenReturn(allCars);

        given().
                contentType(MediaType.APPLICATION_JSON_VALUE).
                when().
                get("/api/cars").
                then().
                statusCode(200).
                body("$", hasSize(2)).
                body("[0].maker", equalTo(car.getMaker())).
                body("[1].maker", equalTo(car2.getMaker()))
                .statusCode(200);


        verify(carManagerService, times(1)).getAllCars();
    }
}
