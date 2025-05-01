package com.homeypark.web_service.reservations.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UpdateReservationResource(
        Integer hoursRegistered,
        Double totalFare,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
