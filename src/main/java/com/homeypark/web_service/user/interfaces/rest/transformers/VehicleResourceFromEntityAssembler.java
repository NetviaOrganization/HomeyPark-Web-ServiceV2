package com.homeypark.web_service.user.interfaces.rest.transformers;


import com.homeypark.web_service.user.domain.model.entities.Vehicle;
import com.homeypark.web_service.user.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        return new VehicleResource(
                entity.getId(),
                entity.getLicensePlate(),
                entity.getBrand(),
                entity.getModel(),
                entity.getProfile().getId()
        );
    }
}
