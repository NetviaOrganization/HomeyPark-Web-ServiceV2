package com.homeypark.web_service.user.interfaces.rest.resources;

public record CreateProfileResource(
        String name,
        String lastName,
        String address,
        String email,
        String idCardType,
        String idCardNumber,
        String type
) {
}
