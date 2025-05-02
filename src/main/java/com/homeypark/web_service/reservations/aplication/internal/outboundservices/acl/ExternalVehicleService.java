package com.homeypark.web_service.reservations.aplication.internal.outboundservices.acl;

import com.homeypark.web_service.user.interfaces.acl.VehiclesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalVehicleService {
    private final VehiclesContextFacade vehiclesContextFacade;

    public ExternalVehicleService(VehiclesContextFacade vehiclesContextFacade) {
        this.vehiclesContextFacade = vehiclesContextFacade;
    }

    public boolean checkVehicleExistById(Long vehicleId){
        return vehiclesContextFacade.checkVehicleExistById(vehicleId);
    }
}
