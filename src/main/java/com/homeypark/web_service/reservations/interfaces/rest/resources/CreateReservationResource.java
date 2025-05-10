package com.homeypark.web_service.reservations.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateReservationResource(
        Integer hoursRegistered,
        Double totalFare,
        LocalDate reservationDate,
        LocalTime startTime,
        LocalTime endTime,
        Long guestId,
        Long hostId,
        Long parkingId,
        Long vehicleId) {}
