package com.homeypark.web_service.parkings.interfaces.rest.resources;

import java.time.LocalTime;

public record UpdateScheduleResource(
        String day,
        LocalTime startTime,
        LocalTime endTime
) {
}
