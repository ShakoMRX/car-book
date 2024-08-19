package com.car.carbook.model.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "CAR_SERVICES")
public class CarService {

    private static final String SEQUENCE_NAME = "SEQ_CAR_SERVICES";
    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    private  Long id;
    private String description;
    private Long userId;
    private Long carId;
    private Timestamp createTime;
    private Double price;

    public CarService(Long id, String description, Long userId, Long carId, Timestamp createTime, Double price) {
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.carId = carId;
        this.createTime = createTime;
        this.price = price;
    }

    public CarService() {

    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCarId() {
        return carId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
