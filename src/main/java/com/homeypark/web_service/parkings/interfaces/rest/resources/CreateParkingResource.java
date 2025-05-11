package com.homeypark.web_service.parkings.interfaces.rest.resources;

import com.homeypark.web_service.parkings.domain.model.valueobjects.ProfileId;

public record CreateParkingResource(
        Long profileId,
        Double width,
        Double length,
        Double height,
        Double price,
        String phone,
        Integer space,
        String description,
        String address,
        String numDirection,
        String street,
        String district,
        String city,
        Double latitude,
        Double longitude
) {
}
