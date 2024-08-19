package com.car.carbook.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "CAR_MODELS")
public class CarModel {

    private static final String SEQUENCE_NAME = "SEQ_CAR_MODEL";
    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    private  Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CAR_BRAND_ID", nullable = false)
    private Long carBrandId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }
}
