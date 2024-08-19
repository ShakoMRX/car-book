package com.car.carbook.controller;


import com.car.carbook.model.request.CarModelRequest;
import com.car.carbook.model.response.CarModelResponse;
import com.car.carbook.service.CarModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/car-models")
@Tag(name = "Car Models", description = "API for managing car models")
public class CarModelController {

    private static final Logger logger = LoggerFactory.getLogger(CarModelController.class);


    private final CarModelService service;

    public CarModelController(CarModelService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new CarModel record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "CarModel created successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarModelResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CarModelResponse> createCarModel(@RequestBody CarModelRequest request) {
        logger.info("Received request to create CarModel");
        CarModelResponse response = service.createCarModel(request);
        logger.info("CarModel created with ID: {}", response.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get CarModel by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "CarModel found",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarModelResponse.class))),
        @ApiResponse(responseCode = "404", description = "CarModel not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarModelResponse> getCarModelById(@PathVariable Long id) {
        logger.info("Received request to get CarModel by ID: {}", id);
        Optional<CarModelResponse> response = service.getCarModelById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("CarModel with ID: {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Operation(summary = "Get all CarModel records with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of CarModel records",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @GetMapping
    public ResponseEntity<Page<CarModelResponse>> getAllCarModels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Received request to get all CarModels with pagination: page={}, size={}", page, size);
        Page<CarModelResponse> response = service.getAllCarModels(page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an existing CarModel record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "CarModel updated successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarModelResponse.class))),
        @ApiResponse(responseCode = "404", description = "CarModel not found", content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarModelResponse> updateCarModel(
            @PathVariable Long id,
            @RequestBody CarModelRequest request) {
        logger.info("Received request to update CarModel with ID: {}", id);
        Optional<CarModelResponse> response = service.updateCarModel(id, request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("CarModel with ID: {} not found for update", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Operation(summary = "Delete a CarModel record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "CarModel deleted successfully"),
        @ApiResponse(responseCode = "404", description = "CarModel not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarModel(@PathVariable Long id) {
        logger.info("Received request to delete CarModel with ID: {}", id);
        service.deleteCarModel(id);
        logger.info("CarModel with ID: {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}
