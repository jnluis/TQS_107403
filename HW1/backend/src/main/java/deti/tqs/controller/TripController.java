package deti.tqs.controller;
import deti.tqs.model.Trip;
import deti.tqs.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/trip")
@Tag(name = "Trip", description = "Operations for trips")
public class TripController {
    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TripService TripService;

    @GetMapping()
    @Operation(summary = "Get all types of grapes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
    })
    public ResponseEntity<List<Trip>> getAllTrips(){
        try {
            return ResponseEntity.ok(TripService.getTrip());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
