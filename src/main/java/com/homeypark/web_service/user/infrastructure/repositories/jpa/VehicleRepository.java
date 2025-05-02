package com.homeypark.web_service.user.infrastructure.repositories.jpa;

import com.homeypark.web_service.user.domain.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findVehicleByProfileId(Long profileId);
}