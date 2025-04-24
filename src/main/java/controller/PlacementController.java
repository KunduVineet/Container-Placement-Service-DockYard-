package controller;

import model.PlacementRequest;
import model.PlacementResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ContainerPlacementService;

@RestController
@RequestMapping("/api")
public class PlacementController {

    private final ContainerPlacementService service;

    public PlacementController(ContainerPlacementService service) {
        this.service = service;
    }

    //POST Endpoint to pick the best slot
    public ResponseEntity<PlacementResponse> pickspot(@RequestBody PlacementRequest request) {
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
