package com.homeypark.web_service.vehicles.interfaces.rest.resources;

import jakarta.validation.constraints.*;

public record CreateVehicleResource(
        @NotNull(message = "License plate cannot be null")
        String licensePlate,
        @NotNull(message = "Model cannot be null")
        String model,
        @NotNull(message = "Brand cannot be null")
        String brand,
        @NotNull(message = "Profile ID cannot be null")
        Long profileId
) {}