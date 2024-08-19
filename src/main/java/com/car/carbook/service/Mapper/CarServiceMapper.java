package com.car.carbook.service.Mapper;

import com.car.carbook.model.entity.CarService;
import com.car.carbook.model.request.CarServiceRequestDto;
import com.car.carbook.model.response.CarServiceResponseDto;
import com.car.carbook.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CarServiceMapper {

    private final UserService userService;

    public CarServiceMapper(UserService userService) {
        this.userService = userService;
    }

    public CarService toEntity(CarServiceRequestDto dto) {
        CarService carService = new CarService();
        carService.setDescription(dto.getDescription());
        carService.setUserId(userService.getAuthorizedUserId());
        carService.setCarId(dto.getCarId());
        carService.setCreateTime(dto.getCreateTime());
        carService.setPrice(dto.getPrice());
        return carService;
    }

    public CarServiceResponseDto toDto(CarService carService) {
        CarServiceResponseDto dto = new CarServiceResponseDto();
        dto.setId(carService.getId());
        dto.setDescription(carService.getDescription());
        dto.setUserId(carService.getUserId());
        dto.setCarId(carService.getCarId());
        dto.setCreateTime(carService.getCreateTime());
        dto.setPrice(carService.getPrice());
        return dto;
    }
}
