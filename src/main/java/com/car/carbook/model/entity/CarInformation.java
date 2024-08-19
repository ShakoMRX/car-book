package com.car.carbook.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "CAR_INFORMATION")
public class CarInformation {

    private static final String SEQUENCE_NAME = "SEQ_CAR_INFORMATION";

    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, nullable = false)
    private String vinCode;
    private String carNumber;
    private Long carBrandId;
    private Long carModelId;
    private String color;
    private Long createdByUserId;

    public Long getId() {
        return id;
    }

    public String getVinCode() {
        return vinCode;
    }

    public String getColor() {
        return color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }


    public void setColor(String color) {
        this.color = color;
    }

    public Long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
