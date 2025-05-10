package com.homeypark.web_service.parkings.domain.model.commands;

import java.time.LocalDateTime;

public record CreateScheduleCommand(
        Long parkingId,
        String day,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
