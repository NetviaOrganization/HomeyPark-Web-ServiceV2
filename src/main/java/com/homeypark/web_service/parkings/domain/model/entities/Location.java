package com.homeypark.web_service.parkings.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.homeypark.web_service.parkings.domain.model.aggregates.Parking;
import com.homeypark.web_service.parkings.domain.model.commands.UpdateLocationCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonBackReference
    private Parking parking;

    private String address;
    private String numDirection;
    private String street;
    private String district;
    private String city;
    private Double latitude;
    private Double longitude;

    public Location updateLocation(UpdateLocationCommand command) {
        this.address = command.address();
        this.numDirection = command.numDirection();
        this.street = command.street();
        this.district = command.district();
        this.city = command.city();
        this.latitude = command.latitude();
        this.longitude = command.longitude();

        return this;
    }
}