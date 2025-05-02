package com.homeypark.web_service.user.application.internal.acl;

import com.homeypark.web_service.user.domain.model.queries.GetVehicleByIdQuery;
import com.homeypark.web_service.user.domain.services.VehicleQueryService;
import com.homeypark.web_service.user.interfaces.acl.VehiclesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class VehiclesContextFacadeImpl implements VehiclesContextFacade {
    private final VehicleQueryService vehicleQueryService;

    public VehiclesContextFacadeImpl(VehicleQueryService vehicleQueryService) {
        this.vehicleQueryService = vehicleQueryService;
    }

    @Override
    public boolean checkVehicleExistById(Long vehicleId) {
        var getVehicleByIdQuery = new GetVehicleByIdQuery(vehicleId);
        var vehicle = vehicleQueryService.handle(getVehicleByIdQuery);
        return vehicle.isPresent();
    }
}
