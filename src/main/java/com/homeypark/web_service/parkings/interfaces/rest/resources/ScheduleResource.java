package com.homeypark.web_service.parkings.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ScheduleResource(
        Long id,
        Long parkingId,
        String day,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
