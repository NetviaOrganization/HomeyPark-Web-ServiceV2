package com.homeypark.web_service.reservations.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record CreateReservationResource(
        @JsonProperty("hoursRegistered")
        Integer hoursRegistered,
        @JsonProperty("totalAmount") // Mapea "totalAmount" del JSON a totalFare
        Double totalFare,
        @JsonProperty("startDate") // Mapea "startDate" del JSON a startTime
        LocalDateTime startTime,
        @JsonProperty("endDate")   // Mapea "endDate" del JSON a endTime
        LocalDateTime endTime,
        @JsonProperty("guestId") // Aunque coincida, puedes ser explícito
        Long guestId,
        @JsonProperty("hostId")  // Aunque coincida, puedes ser explícito
        Long hostId,
        @JsonProperty("parkingId") // Aunque coincida, puedes ser explícito
        Long parkingId,
        @JsonProperty("vehicleId") // Aunque coincida, puedes ser explícito
        Long vehicleId) {}
