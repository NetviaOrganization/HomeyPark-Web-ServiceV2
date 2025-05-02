package com.homeypark.web_service.user.interfaces.rest.resources;

public record CreateVehicleResource(
        String licensePlate,
        String model,
        String brand,
        Long profileId
) {}