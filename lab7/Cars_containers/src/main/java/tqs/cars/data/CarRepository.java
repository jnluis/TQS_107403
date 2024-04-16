package tqs.cars.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.cars.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    public List<Car> findAll();

}
