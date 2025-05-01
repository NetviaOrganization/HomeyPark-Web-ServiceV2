package com.homeypark.web_service.reservations.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateReservationResource(
        Integer hoursRegistered,
        Double totalFare,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long guestId,
        Long hostId,
        Long parkingId,
        Long vehicleId,
        Long cardId) {}
