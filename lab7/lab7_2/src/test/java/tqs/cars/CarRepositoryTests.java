package tqs.cars;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tqs.cars.data.CarRepository;

import static org.assertj.core.api.Assertions.assertThat;
import tqs.cars.model.Car;

import java.util.List;

/**
 * DataJpaTest limits the test scope to the data access context (no web environment loaded, for example)
 * tries to autoconfigure the database, if possible (e.g.: in memory db)
 */
@DataJpaTest
class CarRepositoryTests {

    // get a test-friendly Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository CarRepository;

    @Test
    void whenFindPolestarByMaker_thenReturnPolestarCar() {
        // arrange a new employee and insert into db
        Car Polestar = new Car("Polestar", "2");
        entityManager.persistAndFlush(Polestar); //ensure data is persisted at this point

        // test the query method of interest
        Car found = CarRepository.findById(Polestar.getCarId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found).isEqualTo(Polestar);
    }

    @Test
    void whenInvalidCarID_thenReturnNull() {
        Car fromDb = CarRepository.findById(-9999L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car car_1 = new Car("Volvo", "X40");
        Car car_2 = new Car("Fisher", "Forty");
        Car car_3 = new Car("Redbull", "RR23");

        entityManager.persist(car_1);
        entityManager.persist(car_2);
        entityManager.persist(car_3);
        entityManager.flush();

        List<Car> allEmployees = CarRepository.findAll();

        assertThat(allEmployees).hasSize(3).extracting(Car::getMaker).containsOnly(car_1.getMaker(), car_2.getMaker(), car_3.getMaker());
    }

}