package tqs.cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.cars.data.CarRepository;
import tqs.cars.model.Car;
import tqs.cars.services.CarManagerService;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Test scenario: verify the logic of the Service, mocking the response of the datasource
 * Results in standard unit test with mocks
 */
@ExtendWith(MockitoExtension.class)
class CarServiceTests {

    // mocking the responses of the repository (i.e., no database will be used)
    // lenient is required because we load more expectations in the setup
    // than those used in some tests. As an alternative, the expectations
    // could move into each test method and be trimmed (no need for lenient then)
    @Mock( lenient = true)
    private CarRepository CarRepository;

    @InjectMocks
    private CarManagerService service;

    @Test
    void FindAllTest() throws Exception {
        Car car_1 = new Car("Volvo", "X40");
        Car car_2 = new Car("Fisher", "Forty");
        Car car_3 = new Car("Redbull", "RR23");

        when(CarRepository.findAll()).thenReturn(Arrays.asList(car_1, car_2, car_3));

        assertThat(service.getAllCars())
                .hasSize(3)
                .extracting(Car::getMaker)
                .contains(car_1.getMaker(), car_2.getMaker(), car_3.getMaker());

        verify(CarRepository, times(1)).findAll();

    }

    @Test
    void SaveTest() throws Exception {
        Car volvo = new Car("Volvo", "X40");

        when(CarRepository.save(Mockito.any())).thenReturn(volvo);

        assertThat(service.save(volvo)).isEqualTo(volvo);
    }

    @Test
    void GetCarDetailsTest() throws Exception {
        Car volvo = new Car("Volvo", "X40");
        volvo.setCarId(1L);

        when(CarRepository.findByCarId(1L)).thenReturn(volvo);

        assertThat(service.getCarDetails(1L).get()).isEqualTo(volvo);
    }
}
