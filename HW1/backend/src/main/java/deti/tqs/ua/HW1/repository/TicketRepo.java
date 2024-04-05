package deti.tqs.ua.HW1.repository;

import java.util.List;
import deti.tqs.ua.HW1.model.TicketDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends JpaRepository<TicketDetails, Integer> {
    TicketDetails findById(String id);
    List<TicketDetails> findByTripID(int tripID);
    void deleteByTripID(int tripID);
}
