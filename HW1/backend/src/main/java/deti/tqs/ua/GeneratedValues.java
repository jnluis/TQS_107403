package deti.tqs.ua;
import deti.tqs.ua.model.Trip;
import deti.tqs.ua.repository.TripRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
@Component
//@Generated
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev")
public class GeneratedValues implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(GeneratedValues.class);

    private final TripRepo tripRepo;

    @Autowired
    public GeneratedValues(TripRepo tripRepo) {
        this.tripRepo = tripRepo;
    }

    @Override
    public void run(org.springframework.boot.ApplicationArguments args) throws Exception {
        logger.info("Generating values for the database");

        String aveiro = "Aveiro";
        String viseu = "Viseu";
        String coimbra = "Coimbra";
        String leiria = "Leiria";
        String lisboa = "Lisboa";
        String porto = "Porto";
        String braganca = "Braganca";
        String date1 = "2024-04-01";
        String date2 = "2024-04-02";
        String date3 = "2024-04-03";
        String date4 = "2024-04-04";

        // Generate trips
        Trip trip1 = new Trip();
        trip1.setBusNumber("10");
        trip1.setOrigin(aveiro);
        trip1.setDestination(viseu);
        trip1.setDate(date1);
        trip1.setTime("10:00");
        trip1.setPrice(3.50);

        Trip trip2 = new Trip();
        trip2.setBusNumber("11");
        trip2.setOrigin(aveiro);
        trip2.setDestination(viseu);
        trip2.setDate(date1);
        trip2.setTime("15:00");
        trip2.setPrice(3.50);

        Trip trip3 = new Trip();
        trip3.setBusNumber("10");
        trip3.setOrigin(aveiro);
        trip3.setDestination(viseu);
        trip3.setDate(date1);
        trip3.setTime("21:00");
        trip3.setPrice(3.50);

        Trip trip4 = new Trip();
        trip4.setBusNumber("30A");
        trip4.setOrigin(aveiro);
        trip4.setDestination(braganca);
        trip4.setDate(date1);
        trip4.setTime("21:30");
        trip4.setPrice(10.0);

        Trip trip5 = new Trip();
        trip5.setBusNumber("30B");
        trip5.setOrigin(aveiro);
        trip5.setDestination(lisboa);
        trip5.setDate(date2);
        trip5.setTime("7:30");
        trip5.setPrice(11.0);

        Trip trip6 = new Trip();
        trip6.setBusNumber("456");
        trip6.setOrigin(coimbra);
        trip6.setDestination(leiria);
        trip6.setDate(date2);
        trip6.setTime("12:30");
        trip6.setPrice(2.99);

        Trip trip7 = new Trip();
        trip7.setBusNumber("458");
        trip7.setOrigin(leiria);
        trip7.setDestination(coimbra);
        trip7.setDate(date3);
        trip7.setTime("7:30");
        trip7.setPrice(2.99);

        Trip trip8 = new Trip();
        trip8.setBusNumber("30A");
        trip8.setOrigin(lisboa);
        trip8.setDestination(coimbra);
        trip8.setDate(date4);
        trip8.setTime("6:30");
        trip8.setPrice(85.50);

        Trip trip9 = new Trip();
        trip9.setBusNumber("1L");
        trip9.setOrigin(lisboa);
        trip9.setDestination(porto);
        trip9.setDate(date4);
        trip9.setTime("10:30");
        trip9.setPrice(15.0);

        Trip trip10 = new Trip();
        trip10.setBusNumber("1P");
        trip10.setOrigin(porto);
        trip10.setDestination(lisboa);
        trip10.setDate(date1);
        trip10.setTime("20:30");
        trip10.setPrice(15.0);

        Trip trip11 = new Trip();
        trip11.setBusNumber("12");
        trip11.setOrigin(viseu);
        trip11.setDestination(aveiro);
        trip11.setDate(date2);
        trip11.setTime("12:00");
        trip11.setPrice(3.50);

        Trip trip12 = new Trip();
        trip12.setBusNumber("B12");
        trip12.setOrigin(braganca);
        trip12.setDestination(aveiro);
        trip12.setDate(date3);
        trip12.setTime("18:00");
        trip12.setPrice(12.50);

        Trip trip13 = new Trip();
        trip13.setBusNumber("30C");
        trip13.setOrigin(lisboa);
        trip13.setDestination(braganca);
        trip13.setDate(date3);
        trip13.setTime("14:00");
        trip13.setPrice(15.00);

        Trip trip14 = new Trip();
        trip14.setBusNumber("457");
        trip14.setOrigin(leiria);
        trip14.setDestination(viseu);
        trip14.setDate(date4);
        trip14.setTime("11:00");
        trip14.setPrice(5.99);

        Trip trip15 = new Trip();
        trip15.setBusNumber("4P");
        trip15.setOrigin(porto);
        trip15.setDestination(coimbra);
        trip15.setDate(date4);
        trip15.setTime("17:00");
        trip15.setPrice(7.00);

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
        tripRepo.save(trip11);
        tripRepo.save(trip12);
        tripRepo.save(trip13);
        tripRepo.save(trip14);
        tripRepo.save(trip15);
    }
}
