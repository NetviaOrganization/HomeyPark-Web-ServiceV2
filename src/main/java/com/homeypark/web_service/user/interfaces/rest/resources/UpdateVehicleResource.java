package com.homeypark.web_service.user.interfaces.rest.resources;

public record UpdateVehicleResource(
        String licensePlate,
        String model,
        String brand
) {
}
