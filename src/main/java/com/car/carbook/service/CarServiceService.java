package com.car.carbook.service;


import com.car.carbook.model.entity.CarService;
import com.car.carbook.model.request.CarServiceRequestDto;
import com.car.carbook.model.response.CarServiceResponseDto;
import com.car.carbook.repository.CarServiceRepository;
import com.car.carbook.service.Mapper.CarServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceService {


    private final CarServiceRepository carServiceRepository;
    private final UserService userService;


    private final CarServiceMapper carServiceMapper;

    public CarServiceService(CarServiceRepository carServiceRepository, UserService userService, CarServiceMapper carServiceMapper) {
        this.carServiceRepository = carServiceRepository;
        this.userService = userService;
        this.carServiceMapper = carServiceMapper;
    }

    public CarServiceResponseDto createCarService(CarServiceRequestDto dto) {
        CarService carService = carServiceMapper.toEntity(dto);
        carService = carServiceRepository.save(carService);
        return carServiceMapper.toDto(carService);
    }

    public CarServiceResponseDto updateCarService(Long id, CarServiceRequestDto dto) {
        Optional<CarService> optionalCarService = carServiceRepository.findById(id);
        if (optionalCarService.isPresent()) {
            CarService carService = optionalCarService.get();
            carService.setDescription(dto.getDescription());
            carService.setUserId(userService.getAuthorizedUserId());
            carService.setCarId(dto.getCarId());
            carService.setCreateTime(dto.getCreateTime());
            carService.setPrice(dto.getPrice());
            carService = carServiceRepository.save(carService);
            return carServiceMapper.toDto(carService);
        } else {
            throw new RuntimeException("CarService not found");
        }
    }

    public void deleteCarService(Long id) {
        carServiceRepository.deleteById(id);
    }

    public CarServiceResponseDto getCarServiceById(Long id) {
        Optional<CarService> carService = carServiceRepository.findById(id);
        return carService.map(carServiceMapper::toDto).orElseThrow(() -> new RuntimeException("CarService not found"));
    }

    public Page<CarServiceResponseDto> getCarServicesByUserIdAndServiceName( String serviceName, Pageable pageable) {
        Page<CarService> carServices = carServiceRepository.findByUserIdAndServiceNameLikeOrderByCreateTimeDesc(userService.getAuthorizedUserId(), serviceName, pageable);
        return carServices.map(carServiceMapper::toDto);
    }
}
