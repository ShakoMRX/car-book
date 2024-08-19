package com.car.carbook.service.Mapper;


import com.car.carbook.model.entity.CarBrand;
import com.car.carbook.model.request.CarBrandRequest;
import com.car.carbook.model.response.CarBrandResponse;
import org.springframework.stereotype.Component;

@Component
public class CarBrandMapper {

    public CarBrand toEntity(CarBrandRequest request) {
        CarBrand carBrand = new CarBrand();
        carBrand.setName(request.getName());
        return carBrand;
    }

    public CarBrandResponse toResponse(CarBrand entity) {
        CarBrandResponse response = new CarBrandResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }
}
