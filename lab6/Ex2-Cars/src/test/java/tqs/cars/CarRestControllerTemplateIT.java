package tqs.cars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import tqs.cars.data.CarRepository;
import tqs.cars.model.Car;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase

// switch AutoConfigureTestDatabase with TestPropertySource to use a real database
@TestPropertySource( locations = "classpath:application-integrationtest.properties")
class CarRestControllerTemplateIT {

    // will need to use the server port for the invocation url
    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    void whenValidInput_thenCreateCar() {
        Car Polestar = new Car("Polestar", "2");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", Polestar, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("Polestar");
    }

    @Test
    void givenCars_whenGetCars_thenStatus200()  {
        createTestCar("Polestar", "2");
        createTestCar("Opel", "Mokka");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("Polestar", "Opel");

    }

    private void createTestCar(String maker, String model) {
        Car emp = new Car(maker, model);
        repository.saveAndFlush(emp);
    }

}
