package com.homeypark.web_service.parkings.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateScheduleResource(
        Long parkingId,
        String day,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
