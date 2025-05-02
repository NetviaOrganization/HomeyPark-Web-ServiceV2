package com.homeypark.web_service.user.application.internal.queryservices;

import com.homeypark.web_service.user.domain.model.entities.Vehicle;
import com.homeypark.web_service.user.domain.model.queries.GetAllVehiclesQuery;
import com.homeypark.web_service.user.domain.model.queries.GetVehicleByIdQuery;
import com.homeypark.web_service.user.domain.model.queries.GetVehiclesByProfileIdQuery;
import com.homeypark.web_service.user.domain.services.VehicleQueryService;
import com.homeypark.web_service.user.infrastructure.repositories.jpa.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryServiceImpl implements VehicleQueryService {

    private final VehicleRepository vehicleRepository;

    public VehicleQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return vehicleRepository.findById(query.vehicleId());
    }

    @Override
    public List<Vehicle> handle(GetAllVehiclesQuery query) {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> handle(GetVehiclesByProfileIdQuery query) {
        return vehicleRepository.findVehicleByProfileId(query.profileId());
    }

}