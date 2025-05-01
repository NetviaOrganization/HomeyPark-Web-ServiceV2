package com.homeypark.web_service.parkings.domain.model.commands;

import java.time.LocalDateTime;

public record CreateScheduleCommand(
        Long parkingId,
        String day,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
    public CreateScheduleCommand {
        if (day == null || day.isBlank()) {
            throw new IllegalArgumentException("Day cannot be null or empty");
        }
        if (startTime == null) {
            throw new IllegalArgumentException("Start time cannot be null");
        }
        if (endTime == null) {
            throw new IllegalArgumentException("End time cannot be null");
        }
    }
}
