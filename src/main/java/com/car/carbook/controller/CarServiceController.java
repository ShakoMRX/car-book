package com.car.carbook.controller;


import com.car.carbook.model.request.CarServiceRequestDto;
import com.car.carbook.model.response.CarServiceResponseDto;
import com.car.carbook.service.CarServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car-services")
@Tag(name = "CarService", description = "Operations related to Car Services")
public class CarServiceController {


    private final CarServiceService carServiceService;

    public CarServiceController(CarServiceService carServiceService) {
        this.carServiceService = carServiceService;
    }

    @Operation(summary = "Create a new Car Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car Service created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarServiceResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CarServiceResponseDto> createCarService(@RequestBody CarServiceRequestDto dto) {
        CarServiceResponseDto responseDto = carServiceService.createCarService(dto);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Update an existing Car Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car Service updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarServiceResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Car Service not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CarServiceResponseDto> updateCarService(@PathVariable Long id, @RequestBody CarServiceRequestDto dto) {
        CarServiceResponseDto responseDto = carServiceService.updateCarService(id, dto);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Delete a Car Service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car Service deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Car Service not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarService(@PathVariable Long id) {
        carServiceService.deleteCarService(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get a Car Service by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car Service retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarServiceResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Car Service not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarServiceResponseDto> getCarServiceById(@PathVariable Long id) {
        CarServiceResponseDto responseDto = carServiceService.getCarServiceById(id);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Get all Car Services with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car Services retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarServiceResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<CarServiceResponseDto>> getCarServicesByUserIdAndServiceName(
            @RequestParam(required = false) String serviceName,
            Pageable pageable) {
        Page<CarServiceResponseDto> page = carServiceService.getCarServicesByUserIdAndServiceName(serviceName, pageable);
        return ResponseEntity.ok(page);
    }
}
