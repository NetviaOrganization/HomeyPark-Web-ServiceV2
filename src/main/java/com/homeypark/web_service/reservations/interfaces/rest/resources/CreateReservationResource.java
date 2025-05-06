package com.homeypark.web_service.reservations.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record CreateReservationResource(
        Integer hoursRegistered,
        Double totalFare,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long guestId,
        Long hostId,
        Long parkingId,
        Long vehicleId) {}
