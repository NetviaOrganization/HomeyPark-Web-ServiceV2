package com.homeypark.web_service.parkings.interfaces.rest.resources;

public record ParkingResource(
        Long id,
        Long profileId,
        Double width,
        Double length,
        Double height,
        Double price,
        String phone,
        String space,
        String description,
        String address,
        String numDirection,
        String street,
        String district,
        String city,
        String coordinates,
        String latitude,
        String longitude,
        String day,
        String startTime,
        String endTime
) {
}
