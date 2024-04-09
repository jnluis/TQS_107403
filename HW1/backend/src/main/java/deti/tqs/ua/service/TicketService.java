package deti.tqs.ua.service;

import deti.tqs.ua.model.TicketDetails;
import deti.tqs.ua.model.Trip;
import deti.tqs.ua.repository.TicketRepo;
import deti.tqs.ua.repository.TripRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepo ticketRepo;
    private final TripRepo tripRepo;

    @Autowired
    public TicketService(TicketRepo ticketRepo, TripRepo tripRepo) {
        this.ticketRepo = ticketRepo;
        this.tripRepo = tripRepo;
    }
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    public List<TicketDetails> findAllTickets() {
        logger.info("Find all tickets");
        return ticketRepo.findAll();
    }

    public List<TicketDetails> findTicketsByID(int tripID) {
        logger.info("Find tickets for trip {}", tripID);
        return ticketRepo.findByTripID(tripID);
    }

    public void deleteTicketByID(int tripID) {
        logger.info("Deleting ticket with tripID {}", tripID);
        ticketRepo.deleteByTripID(tripID);
    }

    public TicketDetails reserveTicket(TicketDetails ticket){
        Trip trip = tripRepo.findById(ticket.getTripID());
        if(trip == null){
            logger.error("Trip not found");
            return null;
        }
        ticketRepo.save(ticket);
        tripRepo.save(trip);

        logger.info("Ticket reserved");
        return ticket;
    }
}
