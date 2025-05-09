package com.homeypark.web_service.parkings.interfaces.rest.resources;

import java.time.LocalTime;

public record ScheduleResource(
        Long id,
        Long parkingId,
        String day,
        LocalTime startTime,
        LocalTime endTime
) {
}
