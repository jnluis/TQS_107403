package deti.tqs.ua.HW1.controller;

import deti.tqs.ua.HW1.model.Trip;
import deti.tqs.ua.HW1.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/trips")
@Tag(name = "Trip", description = "Operations for trips")
public class TripController {
    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TripService tripService;

    @GetMapping("/list")
    @Operation(summary = "Get all trips")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
    })
    public ResponseEntity<List<Trip>> listTrips(@RequestParam(required = false) String origin,
                                                @RequestParam(required = false) String destination,
                                                @RequestParam(required = false) String date ,
                                                @RequestParam(required = false) String currency) {
        logger.info("Trips List requested");
        return ResponseEntity.ok(tripService.listTrips(origin, destination, date, currency));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable int id, @RequestParam(required=false) String currency) {
        logger.info("Trip " + id + " requested");
        return ResponseEntity.ok(tripService.getTrip(id, currency));
    }

    @GetMapping("/dates")
    public ResponseEntity<List<String>> getDates() {
        logger.info("Dates list requested");
        return ResponseEntity.ok(tripService.getDates());
    }

    @GetMapping("/origins")
    public ResponseEntity<List<String>> getOrigins() {
        logger.info("Origins list requested");
        return ResponseEntity.ok(tripService.getOrigins());
    }

    @GetMapping("/destinations")
    public ResponseEntity<List<String>> getDestinations() {
        logger.info("Destinations list requested");
        return ResponseEntity.ok(tripService.getDestinations());
    }
}
