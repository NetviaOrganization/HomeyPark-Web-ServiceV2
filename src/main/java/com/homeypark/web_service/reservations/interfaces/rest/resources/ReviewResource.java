package com.homeypark.web_service.reservations.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ReviewResource(
    Long id,
    Long reservationId,
    Integer stars,
    String comment,
    Long reviewerGuestId,
    Long reviewerHostId,
    Long reviewedGuestId,
    Long reviewedHostId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
