package com.homeypark.web_service.user.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String name,
        String lastName,
        String address,
        String email,
        String idCardType,
        String idCardNumber,
        String type
) {
}
