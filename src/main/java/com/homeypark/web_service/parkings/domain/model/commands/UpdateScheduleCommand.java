package com.homeypark.web_service.parkings.domain.model.commands;

import java.time.LocalDateTime;

public record UpdateScheduleCommand(
        Long scheduleId,
        String day,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
    public UpdateScheduleCommand {
        if (scheduleId == null || scheduleId <= 0) {
            throw new IllegalArgumentException("Schedule ID cannot be null.");
        }
        if (day == null || day.isEmpty()) {
            throw new IllegalArgumentException("Day cannot be null or empty.");
        }
        if (startTime == null) {
            throw new IllegalArgumentException("Start time cannot be null.");
        }
        if (endTime == null) {
            throw new IllegalArgumentException("End time cannot be null.");
        }
    }
}
