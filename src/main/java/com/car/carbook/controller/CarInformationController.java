package com.car.carbook.controller;


import com.car.carbook.exception.BadRequestException;
import com.car.carbook.exception.ErrorCodes;
import com.car.carbook.model.request.CarInformationRequest;
import com.car.carbook.model.response.CarInformationResponse;
import com.car.carbook.service.CarInformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/car-information")
@Tag(name = "Car Information", description = "API for managing car information")
public class CarInformationController {

    private static final Logger logger = LoggerFactory.getLogger(CarInformationController.class);


    private final CarInformationService service;

    public CarInformationController(CarInformationService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new CarInformation record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CarInformation created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarInformationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CarInformationResponse> createCarInformation(@RequestBody CarInformationRequest request) {
        if(request.getVinCode()==null || request.getVinCode().isEmpty()){
            throw new BadRequestException("VIN code cannot be empty", ErrorCodes.DATA_NOT_FOUND,false);
        }
        logger.info("Received request to create CarInformation");
        CarInformationResponse response = service.createCarInformation(request);
        logger.info("CarInformation created with ID: {}", response.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get CarInformation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarInformation found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarInformationResponse.class))),
            @ApiResponse(responseCode = "404", description = "CarInformation not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarInformationResponse> getCarInformationById(@PathVariable Long id) {
        logger.info("Received request to get CarInformation by ID: {}", id);
        Optional<CarInformationResponse> response = service.getCarInformationById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("CarInformation with ID: {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Operation(summary = "Get all CarInformation records with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of CarInformation records",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @GetMapping
    public ResponseEntity<Page<CarInformationResponse>> getAllCarInformation(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String vinCode
    ) {
        logger.info("Received request to get all CarInformation with pagination: page={}, size={}", page, size);
        Page<CarInformationResponse> response = service.getAllCarInformation(vinCode, page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an existing CarInformation record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarInformation updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarInformationResponse.class))),
            @ApiResponse(responseCode = "404", description = "CarInformation not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarInformationResponse> updateCarInformation(
            @PathVariable Long id,
            @RequestBody CarInformationRequest request) {
        logger.info("Received request to update CarInformation with ID: {}", id);
        Optional<CarInformationResponse> response = service.updateCarInformation(id, request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("CarInformation with ID: {} not found for update", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Operation(summary = "Delete a CarInformation record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "CarInformation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "CarInformation not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarInformation(@PathVariable Long id) {
        logger.info("Received request to delete CarInformation with ID: {}", id);
        service.deleteCarInformation(id);
        logger.info("CarInformation with ID: {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}
