package com.car.carbook.service;


import com.car.carbook.model.entity.CarInformation;
import com.car.carbook.model.request.CarInformationRequest;
import com.car.carbook.model.response.CarInformationResponse;
import com.car.carbook.repository.CarInformationRepository;
import com.car.carbook.service.Mapper.CarInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarInformationService {

    private static final Logger logger = LoggerFactory.getLogger(CarInformationService.class);


    private  final CarInformationRepository repository;

    private final CarInformationMapper mapper;

    private final UserService userService;

    public CarInformationService(CarInformationRepository repository, CarInformationMapper mapper, UserService userService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public CarInformationResponse createCarInformation(CarInformationRequest request) {
        logger.info("Creating new CarInformation with VIN code: {}", request.getVinCode());

        CarInformation carInformation = mapper.toEntity(request);
        CarInformation savedCarInformation = repository.save(carInformation);
        logger.info("Created CarInformation with ID: {}", savedCarInformation.getId());

        return mapper.toResponse(savedCarInformation);
    }

    public Optional<CarInformationResponse> getCarInformationById(Long id) {
        logger.info("Fetching CarInformation with ID: {}", id);
        return repository.findById(id).map(mapper::toResponse);
    }

    public Page<CarInformationResponse> getAllCarInformation(String vinCode,int page, int size) {
        logger.info("Fetching all CarInformation entries with pagination: page={}, size={}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return repository.getCarInformation(vinCode,userService.getAuthorizedUserId(),pageable).map(mapper::toResponse);
    }

    public Optional<CarInformationResponse> updateCarInformation(Long id, CarInformationRequest request) {
        logger.info("Updating CarInformation with ID: {}", id);
        return repository.findById(id).map(carInformation -> {
            carInformation.setVinCode(request.getVinCode());
            carInformation.setCarBrandId(request.getCarBrandId());
            carInformation.setCarModelId(request.getCarModelId());
            carInformation.setColor(request.getColor());

            CarInformation updatedCarInformation = repository.save(carInformation);
            logger.info("Updated CarInformation with ID: {}", updatedCarInformation.getId());
            return mapper.toResponse(updatedCarInformation);
        });
    }

    public void deleteCarInformation(Long id) {
        logger.info("Deleting CarInformation with ID: {}", id);
        repository.deleteById(id);
    }
}
