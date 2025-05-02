package com.homeypark.web_service.user.interfaces.rest.transformers;

import com.homeypark.web_service.user.domain.model.commands.UpdateVehicleCommand;
import com.homeypark.web_service.user.interfaces.rest.resources.UpdateVehicleResource;

public class UpdateVehicleCommandFromResource {
    public static UpdateVehicleCommand toCommandFromResource(Long vehicleId, UpdateVehicleResource resource) {
        return new UpdateVehicleCommand(vehicleId, resource.licensePlate(), resource.model(), resource.brand());
    }
}
