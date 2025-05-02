package com.homeypark.web_service.reservations.domain.model.entities;

import com.homeypark.web_service.reservations.domain.model.commands.CreateReservationCommand;
import com.homeypark.web_service.reservations.domain.model.commands.UpdateReservationCommand;
import com.homeypark.web_service.reservations.domain.model.commands.UpdateStatusCommand;
import com.homeypark.web_service.reservations.domain.model.valueobject.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer hoursRegistered;
    private Double totalFare;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private GuestId guestId;

    @Embedded
    private HostId hostId;

    @Embedded
    private ParkingId parkingId;

    @Embedded
    private VehicleId vehicleId;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Reservation(CreateReservationCommand command) {
        this.hoursRegistered = command.hoursRegistered();
        this.totalFare = command.totalFare();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.hostId = new HostId(command.hostId());
        this.guestId = new GuestId(command.guestId());
        this.parkingId = new ParkingId(command.parkingId());
        this.vehicleId = new VehicleId(command.vehicleId());
        this.status = Status.Pending;
    }
    public Reservation updatedReservation(UpdateReservationCommand command){
        this.hoursRegistered = command.hoursRegistered();
        this.totalFare = command.totalFare();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        return this;
    }
    public Reservation updatedStatus(UpdateStatusCommand command){
        this.status = command.status();
        return this;
    }
}
