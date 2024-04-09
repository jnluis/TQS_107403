package deti.tqs.ua.controller;

import deti.tqs.ua.model.Trip;
import deti.tqs.ua.service.TripService;
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
@RestController
@RequestMapping(path = "/api/trips")
@CrossOrigin(origins = "*")
@Tag(name = "Trip", description = "Operations for trips")
public class TripController {
    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

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
    @Operation(summary = "Get a trip by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
    })
    public ResponseEntity<Trip> getTrip(@PathVariable int id, @RequestParam(required=false) String currency) {
        logger.info("Trip {} requested",id);

        return ResponseEntity.ok(tripService.getTrip(id, currency));
    }

    @GetMapping("/dates")
    @Operation(summary = "Get all trip dates available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
    })
    public ResponseEntity<List<String>> getDates() {
        logger.info("Dates list requested");
        return ResponseEntity.ok(tripService.getDates());
    }

    @GetMapping("/origins")
    @Operation(summary = "Get all trip origins available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
    })
    public ResponseEntity<List<String>> getOrigins() {
        logger.info("Origins list requested");
        return ResponseEntity.ok(tripService.getOrigins());
    }

    @GetMapping("/destinations")
    @Operation(summary = "Get all trip destinations available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
    })
    public ResponseEntity<List<String>> getDestinations() {
        logger.info("Destinations list requested");
        return ResponseEntity.ok(tripService.getDestinations());
    }
}
