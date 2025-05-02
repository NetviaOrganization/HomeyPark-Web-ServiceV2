package com.homeypark.web_service.reservations.interfaces.rest.resources;


import java.time.LocalDateTime;

public record ReservationResource(
        Long id,
        Integer hoursRegistered,
        Double totalFare,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String status,
        Long guestId,
        Long hostId,
        Long parkingId,
        Long vehicleId
) {
}
