package com.homeypark.web_service.profiles.domain.model.commands;

public record CreateProfileCommand(String name, String lastName, String address, Long userId) {
}
