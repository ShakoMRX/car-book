package com.car.carbook.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "CAR_BRANDS")
public class CarBrand {

    private static final String SEQUENCE_NAME = "SEQ_CAR_BRAND";

    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

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
}
