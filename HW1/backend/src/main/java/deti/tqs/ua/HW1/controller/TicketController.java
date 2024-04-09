package deti.tqs.ua.HW1.controller;

import deti.tqs.ua.HW1.model.TicketDetails;
import deti.tqs.ua.HW1.model.TicketTripInfoDTO;
import deti.tqs.ua.HW1.model.Trip;
import deti.tqs.ua.HW1.service.TicketService;
import deti.tqs.ua.HW1.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(path = "/api/ticket")
@Tag(name = "Trip", description = "Operations for tickets")
public class TicketController {
    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TripService tripService;

    @PostMapping("/reserve")
    @Operation(summary = "Reserve a ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
    })
    public ResponseEntity<TicketDetails> reserveTicket(@RequestBody TicketDetails ticket) {
        logger.info("Requested ticket purchase for trip " + ticket.getTripID());

        if (!tripService.tripExists(ticket.getTripID())) {
            logger.info("Couldnt find trip with id " + ticket.getTripID());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trip not found");
        }

        if (!ticket.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            logger.info("Failed to validate email on ticket purchase " + ticket.getId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        }

        Trip trip = tripService.getTrip(ticket.getTripID(), "EUR");

        if (trip == null) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trip not found");
        }

        TicketDetails t = ticketService.ReserveTicket(ticket);

        return ResponseEntity.ok(t);
    }

    @GetMapping("/list")
    @Operation(summary = "List of reserved tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
    })
    public ResponseEntity<List<TicketTripInfoDTO>> listReservedTickets() {
        List<TicketDetails> tickets = ticketService.findAllTickets();
        List<TicketTripInfoDTO> ticketDTO = new ArrayList<>();

        for (TicketDetails td : tickets) {
            TicketTripInfoDTO ttiDTO = new TicketTripInfoDTO();
            ttiDTO.setId(td.getId());
            ttiDTO.setPrice(String.valueOf(tripService.getTrip(td.getTripID(), "EUR").getPrice()));
            ttiDTO.setTripID(td.getTripID());
            ttiDTO.setFirstName(td.getFirstName());
            ttiDTO.setLastName(td.getLastName());
            ttiDTO.setEmail(td.getEmail());
            ttiDTO.setBusID(tripService.getTrip(td.getTripID(), "EUR").getId());
            ttiDTO.setDate(tripService.getTrip(td.getTripID(), "EUR").getDate());
            ttiDTO.setTime(tripService.getTrip(td.getTripID(), "EUR").getTime());
            ttiDTO.setBusNumber(tripService.getTrip(td.getTripID(), "EUR").getBusNumber());
            ttiDTO.setOrigin(tripService.getTrip(td.getTripID(), "EUR").getOrigin());
            ttiDTO.setDestination(tripService.getTrip(td.getTripID(), "EUR").getDestination());
            ticketDTO.add(ttiDTO);
        }
        return ResponseEntity.ok(ticketDTO);
    }

}
