package com.car.carbook.service;


import com.car.carbook.model.entity.CarModel;
import com.car.carbook.model.request.CarModelRequest;
import com.car.carbook.model.response.CarModelResponse;
import com.car.carbook.repository.CarModelRepository;
import com.car.carbook.service.Mapper.CarModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarModelService {

    private static final Logger logger = LoggerFactory.getLogger(CarModelService.class);

    private  final CarModelRepository repository;


    private final CarModelMapper mapper;

    public CarModelService(CarModelRepository repository, CarModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CarModelResponse createCarModel(CarModelRequest request) {
        logger.info("Creating new CarModel with name: {}", request.getName());

        CarModel carModel = mapper.toEntity(request);
        CarModel savedCarModel = repository.save(carModel);
        logger.info("Created CarModel with ID: {}", savedCarModel.getId());

        return mapper.toResponse(savedCarModel);
    }

    public Optional<CarModelResponse> getCarModelById(Long id) {
        logger.info("Fetching CarModel with ID: {}", id);
        return repository.findById(id).map(mapper::toResponse);
    }

    public Page<CarModelResponse> getAllCarModels(int page, int size) {
        logger.info("Fetching all CarModels entries with pagination: page={}, size={}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public Optional<CarModelResponse> updateCarModel(Long id, CarModelRequest request) {
        logger.info("Updating CarModel with ID: {}", id);
        return repository.findById(id).map(carModel -> {
            carModel.setName(request.getName());
            carModel.setCarBrandId(request.getCarBrandId());

            CarModel updatedCarModel = repository.save(carModel);
            logger.info("Updated CarModel with ID: {}", updatedCarModel.getId());
            return mapper.toResponse(updatedCarModel);
        });
    }

    public void deleteCarModel(Long id) {
        logger.info("Deleting CarModel with ID: {}", id);
        repository.deleteById(id);
    }
}
