package com.homeypark.web_service.parkings.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.homeypark.web_service.parkings.domain.model.aggregates.Parking;
import com.homeypark.web_service.parkings.domain.model.commands.CreateScheduleCommand;
import com.homeypark.web_service.parkings.domain.model.commands.UpdateScheduleCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parking_id", nullable = false)
    @JsonBackReference
    private Parking parking;

    private String day;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Schedule(CreateScheduleCommand command) {
        this.day = command.day();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
    }

    public Schedule updatedSchedule(UpdateScheduleCommand command) {
        this.day = command.day();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        return this;
    }
}