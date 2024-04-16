package tqs.cars;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tqs.cars.model.Car;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class CarControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Container
    @Order(1)
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:14")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("test");

    @DynamicPropertySource
    @Order(2)
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @Order(3)
    public void testCreateCar() {
        Car c = new Car("Tesla", "Model S");
        RestAssuredMockMvc.given().contentType("application/json").mockMvc(mockMvc)
                .body(c)
                .when().post("/api/cars")
                .then().statusCode(201);
    }

    @Test
    @Order(4)
    public void testGetAllCars() {
        RestAssuredMockMvc.given().contentType("application/json").mockMvc(mockMvc)
                .when().get("/api/cars")
                .then().statusCode(200)
                .body("[0].maker", is("Ford"))
                .body("[0].model", is("F150"));
    }

    @Test
    @Order(5)
    void testGetCarByID(){

        RestAssuredMockMvc.given().contentType("application/json").mockMvc(mockMvc)
                .when().get("/api/cars/3")
                .then().statusCode(200)
                .body("maker", is("Polestar"));
    }


}
