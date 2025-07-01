package com.homeypark.web_service.reservations.interfaces.rest.resources;

public record UserReputationResource(
    Long userId,
    Double averageRatingAsHost,
    Double averageRatingAsGuest,
    Integer reviewCountAsHost,
    Integer reviewCountAsGuest
) {}
