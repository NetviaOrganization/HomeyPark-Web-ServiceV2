package com.homeypark.web_service.user.domain.model.commands;

public record UpdateProfileCommand(Long userId, String name, String lastName, String address, String email, String idCardType, String idCardNumber) {
}
