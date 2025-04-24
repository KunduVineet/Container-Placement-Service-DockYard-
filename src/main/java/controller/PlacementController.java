package controller;

import model.PlacementRequest;
import model.PlacementResponse;
import service.ContainerPlacementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// REST controller for handling container placement requests
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Allow all origins for testing
public class PlacementController {
    private final ContainerPlacementService service;

    // Constructor injection for service
    public PlacementController(ContainerPlacementService service) {
        this.service = service;
    }

    // POST endpoint to pick the best slot
    @PostMapping("/pickSpot")
    public ResponseEntity<PlacementResponse> pickSpot(@RequestBody PlacementRequest request) {
        try {
            // Call service to process request and get response
            PlacementResponse response = service.placeContainer(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle invalid JSON or other errors
            PlacementResponse error = new PlacementResponse("Invalid request: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}