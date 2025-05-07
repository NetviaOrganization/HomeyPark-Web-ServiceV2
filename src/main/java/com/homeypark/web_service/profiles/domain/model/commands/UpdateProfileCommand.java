package com.homeypark.web_service.profiles.domain.model.commands;

public record UpdateProfileCommand(Long profileId, String name, String lastName, String address) {
}
