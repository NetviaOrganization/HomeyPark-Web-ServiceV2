package com.homeypark.web_service.reservations.domain.model.commands;

import java.time.LocalDateTime;

public record CreateReservationCommand(
        Integer hoursRegistered,
        Double totalFare,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long guestId,
        Long hostId,
        Long parkingId,
        Long vehicleId,
        Long cardId
) {}
