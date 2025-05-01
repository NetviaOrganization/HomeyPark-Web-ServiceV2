package com.homeypark.web_service.user.domain.model.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.homeypark.web_service.user.domain.model.aggregates.Profile;
import com.homeypark.web_service.user.domain.model.commands.CreateVehicleCommand;
import com.homeypark.web_service.user.domain.model.commands.UpdateVehicleCommand;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "vehicles")

public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String model;
    private String brand;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private Profile profile;

    public Vehicle(String licensePlate, String model, String brand, Profile profile) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.profile = profile;
    }


    public Vehicle(CreateVehicleCommand command, Profile profile) {
        this.licensePlate = command.licensePlate();
        this.model = command.model();
        this.brand = command.brand();
        this.profile = profile;
    }

    public Vehicle updatedVehicle(UpdateVehicleCommand command) {
        this.licensePlate = command.licensePlate();
        this.model = command.model();
        this.brand = command.brand();
        return this;
    }
}