package com.car.carbook.controller;


import com.car.carbook.model.request.CarBrandRequest;
import com.car.carbook.model.response.CarBrandResponse;
import com.car.carbook.service.CarBrandService;
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
@RequestMapping("/api/car-brands")
@Tag(name = "Car Brands", description = "API for managing car brands")
public class CarBrandController {

    private static final Logger logger = LoggerFactory.getLogger(CarBrandController.class);


    private final CarBrandService service;

    public CarBrandController(CarBrandService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new CarBrand record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "CarBrand created successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarBrandResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CarBrandResponse> createCarBrand(@RequestBody CarBrandRequest request) {
        logger.info("Received request to create CarBrand");
        CarBrandResponse response = service.createCarBrand(request);
        logger.info("CarBrand created with ID: {}", response.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get CarBrand by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "CarBrand found",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarBrandResponse.class))),
        @ApiResponse(responseCode = "404", description = "CarBrand not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarBrandResponse> getCarBrandById(@PathVariable Long id) {
        logger.info("Received request to get CarBrand by ID: {}", id);
        Optional<CarBrandResponse> response = service.getCarBrandById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("CarBrand with ID: {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Operation(summary = "Get all CarBrand records with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of CarBrand records",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @GetMapping
    public ResponseEntity<Page<CarBrandResponse>> getAllCarBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Received request to get all CarBrands with pagination: page={}, size={}", page, size);
        Page<CarBrandResponse> response = service.getAllCarBrands(page, size);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an existing CarBrand record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "CarBrand updated successfully",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarBrandResponse.class))),
        @ApiResponse(responseCode = "404", description = "CarBrand not found", content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarBrandResponse> updateCarBrand(
            @PathVariable Long id,
            @RequestBody CarBrandRequest request) {
        logger.info("Received request to update CarBrand with ID: {}", id);
        Optional<CarBrandResponse> response = service.updateCarBrand(id, request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("CarBrand with ID: {} not found for update", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @Operation(summary = "Delete a CarBrand record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "CarBrand deleted successfully"),
        @ApiResponse(responseCode = "404", description = "CarBrand not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarBrand(@PathVariable Long id) {
        logger.info("Received request to delete CarBrand with ID: {}", id);
        service.deleteCarBrand(id);
        logger.info("CarBrand with ID: {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}
