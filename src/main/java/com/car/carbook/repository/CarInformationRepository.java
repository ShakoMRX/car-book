package com.car.carbook.repository;

import com.car.carbook.model.entity.CarInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarInformationRepository extends JpaRepository<CarInformation, Long> {

    @Query("SELECT c FROM CarInformation c " +
            "WHERE (:vinCode IS NULL OR c.vinCode = :vinCode) " +
            "AND c.createdByUserId = :createdByUserId")
    Page<CarInformation> getCarInformation(@Param("vinCode") String vinCode,
                                           @Param("createdByUserId") Long createdByUserId,
                                           Pageable pageable);
}
