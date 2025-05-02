package com.homeypark.web_service.user.interfaces.rest.resources;


public record VehicleResource(
        Long id,
        String licensePlate,
        String model,
        String brand,
        Long profileId
) {
}
