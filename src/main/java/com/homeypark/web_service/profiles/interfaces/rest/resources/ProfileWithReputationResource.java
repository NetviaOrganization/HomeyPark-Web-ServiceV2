package com.homeypark.web_service.profiles.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProfileWithReputationResource(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        Long userId,
        Double averageRatingAsHost,
        Integer totalReviewsAsHost,
        Double averageRatingAsGuest,
        Integer totalReviewsAsGuest,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
