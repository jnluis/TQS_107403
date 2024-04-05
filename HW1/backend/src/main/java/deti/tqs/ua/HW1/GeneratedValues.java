package deti.tqs.ua.HW1;
import deti.tqs.ua.HW1.model.Trip;
import deti.tqs.ua.HW1.repository.TripRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
@Component
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev")
public class GeneratedValues implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(GeneratedValues.class);

    @Autowired
    private TripRepo tripRepo;

    @Override
    public void run(org.springframework.boot.ApplicationArguments args) throws Exception {
        logger.info("Generating values for the database");

        // Generate trips
        Trip trip1 = new Trip();
        trip1.setBusNumber("10");
        trip1.setOrigin("Aveiro");
        trip1.setDestination("Viseu");
        trip1.setDate("2024-04-01");
        trip1.setTime("10:00");
        trip1.setPrice(3.50);

        Trip trip2 = new Trip();
        trip2.setBusNumber("11");
        trip2.setOrigin("Aveiro");
        trip2.setDestination("Viseu");
        trip2.setDate("2024-04-01");
        trip2.setTime("15:00");
        trip2.setPrice(3.50);

        Trip trip3 = new Trip();
        trip3.setBusNumber("10");
        trip3.setOrigin("Aveiro");
        trip3.setDestination("Viseu");
        trip3.setDate("2024-04-01");
        trip3.setTime("21:00");
        trip3.setPrice(3.50);

        Trip trip4 = new Trip();
        trip4.setBusNumber("30A");
        trip4.setOrigin("Aveiro");
        trip4.setDestination("Braganca");
        trip4.setDate("2024-04-01");
        trip4.setTime("21:30");
        trip4.setPrice(10.0);

        Trip trip5 = new Trip();
        trip5.setBusNumber("30B");
        trip5.setOrigin("Aveiro");
        trip5.setDestination("Lisboa");
        trip5.setDate("2024-04-02");
        trip5.setTime("7:30");
        trip5.setPrice(11.0);

        Trip trip6 = new Trip();
        trip6.setBusNumber("456");
        trip6.setOrigin("Coimbra");
        trip6.setDestination("Leiria");
        trip6.setDate("2024-04-02");
        trip6.setTime("12:30");
        trip6.setPrice(2.99);

        Trip trip7 = new Trip();
        trip7.setBusNumber("458");
        trip7.setOrigin("Leiria");
        trip7.setDestination("Coimbra");
        trip7.setDate("2024-04-03");
        trip7.setTime("7:30");
        trip7.setPrice(2.99);

        Trip trip8 = new Trip();
        trip8.setBusNumber("30A");
        trip8.setOrigin("Lisboa");
        trip8.setDestination("Coimbra");
        trip8.setDate("2024-04-04");
        trip8.setTime("6:30");
        trip8.setPrice(85.50);

        Trip trip9 = new Trip();
        trip9.setBusNumber("1L");
        trip9.setOrigin("Lisboa");
        trip9.setDestination("Porto");
        trip9.setDate("2024-04-01");
        trip9.setTime("10:30");
        trip9.setPrice(15.0);

        Trip trip10 = new Trip();
        trip10.setBusNumber("1P");
        trip10.setOrigin("Porto");
        trip10.setDestination("Lisboa");
        trip10.setDate("2024-04-01");
        trip10.setTime("20:30");
        trip10.setPrice(15.0);

        tripRepo.save(trip1);
        tripRepo.save(trip2);
        tripRepo.save(trip3);
        tripRepo.save(trip4);
        tripRepo.save(trip5);
        tripRepo.save(trip6);
        tripRepo.save(trip7);
        tripRepo.save(trip8);
        tripRepo.save(trip9);
        tripRepo.save(trip10);
    }
}
