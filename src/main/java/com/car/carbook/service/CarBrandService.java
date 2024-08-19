package com.car.carbook.service;


import com.car.carbook.model.entity.CarBrand;
import com.car.carbook.model.request.CarBrandRequest;
import com.car.carbook.model.response.CarBrandResponse;
import com.car.carbook.repository.CarBrandRepository;
import com.car.carbook.service.Mapper.CarBrandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarBrandService {

    private static final Logger logger = LoggerFactory.getLogger(CarBrandService.class);


    private  final CarBrandRepository repository;


    private final CarBrandMapper mapper;

    public CarBrandService(CarBrandRepository repository, CarBrandMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CarBrandResponse createCarBrand(CarBrandRequest request) {
        logger.info("Creating new CarBrand with name: {}", request.getName());

        CarBrand carBrand = mapper.toEntity(request);
        CarBrand savedCarBrand = repository.save(carBrand);
        logger.info("Created CarBrand with ID: {}", savedCarBrand.getId());

        return mapper.toResponse(savedCarBrand);
    }

    public Optional<CarBrandResponse> getCarBrandById(Long id) {
        logger.info("Fetching CarBrand with ID: {}", id);
        return repository.findById(id).map(mapper::toResponse);
    }

    public Page<CarBrandResponse> getAllCarBrands(int page, int size) {
        logger.info("Fetching all CarBrands entries with pagination: page={}, size={}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public Optional<CarBrandResponse> updateCarBrand(Long id, CarBrandRequest request) {
        logger.info("Updating CarBrand with ID: {}", id);
        return repository.findById(id).map(carBrand -> {
            carBrand.setName(request.getName());

            CarBrand updatedCarBrand = repository.save(carBrand);
            logger.info("Updated CarBrand with ID: {}", updatedCarBrand.getId());
            return mapper.toResponse(updatedCarBrand);
        });
    }

    public void deleteCarBrand(Long id) {
        logger.info("Deleting CarBrand with ID: {}", id);
        repository.deleteById(id);
    }
}
