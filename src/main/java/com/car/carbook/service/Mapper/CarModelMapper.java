package com.car.carbook.service.Mapper;


import com.car.carbook.model.entity.CarModel;
import com.car.carbook.model.request.CarModelRequest;
import com.car.carbook.model.response.CarModelResponse;
import org.springframework.stereotype.Component;

@Component
public class CarModelMapper {

    public CarModel toEntity(CarModelRequest request) {
        CarModel carModel = new CarModel();
        carModel.setName(request.getName());
        carModel.setCarBrandId(request.getCarBrandId());
        return carModel;
    }

    public CarModelResponse toResponse(CarModel entity) {
        CarModelResponse response = new CarModelResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setCarBrandId(entity.getCarBrandId());
        return response;
    }
}
