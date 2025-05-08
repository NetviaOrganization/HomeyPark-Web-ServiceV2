package com.homeypark.web_service.vehicles.interfaces.rest.resources;

public record CreateVehicleResource(
        String licensePlate,
        String model,
        String brand,
        Long profileId
) {}