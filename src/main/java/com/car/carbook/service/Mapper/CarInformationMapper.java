package com.car.carbook.service.Mapper;


import com.car.carbook.model.entity.CarInformation;
import com.car.carbook.model.request.CarInformationRequest;
import com.car.carbook.model.response.CarInformationResponse;
import com.car.carbook.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CarInformationMapper {

    private final UserService userService;

    public CarInformationMapper(UserService userService) {
        this.userService = userService;
    }

    public CarInformation toEntity(CarInformationRequest request) {
        CarInformation carInformation = new CarInformation();
        carInformation.setVinCode(request.getVinCode());
        carInformation.setCarBrandId(request.getCarBrandId());
        carInformation.setCarModelId(request.getCarModelId());
        carInformation.setColor(request.getColor());
        carInformation.setCreatedByUserId(userService.getAuthorizedUserId());
        return carInformation;
    }

    public CarInformationResponse toResponse(CarInformation entity) {
        CarInformationResponse response = new CarInformationResponse();
        response.setId(entity.getId());
        response.setVinCode(entity.getVinCode());
        response.setCarBrandId(entity.getCarBrandId());
        response.setCarModelId(entity.getCarModelId());
        response.setColor(entity.getColor());
        return response;
    }
}
