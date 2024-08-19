package com.car.carbook.repository;

import com.car.carbook.model.entity.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarServiceRepository extends JpaRepository<CarService, Long> {

    @Query("SELECT c FROM CarService c " +
            "WHERE c.userId = :userId " +
            "AND (:serviceName IS NULL OR c.description LIKE %:serviceName%) " +
            "ORDER BY c.createTime DESC")
    Page<CarService> findByUserIdAndServiceNameLikeOrderByCreateTimeDesc(
            @Param("userId") Long userId,
            @Param("serviceName") String serviceName,
            Pageable pageable);
}
