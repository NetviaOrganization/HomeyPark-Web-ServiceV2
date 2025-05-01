package com.homeypark.web_service.user.domain.model.commands;

public record CreateVehicleCommand(String licensePlate, String model, String brand, Long userId) {
}
