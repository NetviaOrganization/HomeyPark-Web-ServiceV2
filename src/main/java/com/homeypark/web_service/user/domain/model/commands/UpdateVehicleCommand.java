package com.homeypark.web_service.user.domain.model.commands;

public record UpdateVehicleCommand(Long vehicleId, String licensePlate, String model, String brand) {
}
