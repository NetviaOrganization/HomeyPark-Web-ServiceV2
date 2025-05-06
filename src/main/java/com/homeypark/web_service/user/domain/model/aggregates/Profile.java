package com.homeypark.web_service.user.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.homeypark.web_service.user.domain.model.commands.CreateProfileCommand;
import com.homeypark.web_service.user.domain.model.commands.UpdateProfileCommand;
import com.homeypark.web_service.user.domain.model.entities.Vehicle;
import com.homeypark.web_service.user.domain.model.valueobject.ProfileType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String address;
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private ProfileType type;
    private LocalDateTime dateCreated;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    @JsonManagedReference
    private List<Vehicle> vehicles = new ArrayList<>();

    public Profile(Long id, String lastName, String name, LocalDateTime dateCreated) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.dateCreated = dateCreated;
        this.vehicles = new ArrayList<>();
    }

    public Profile(CreateProfileCommand command) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.email = command.email();
        this.address = command.address();
        this.type = ProfileType.valueOf(command.type());
        this.vehicles = new ArrayList<>();
        this.dateCreated = LocalDateTime.now();
    }
    public Profile updatedProfile(UpdateProfileCommand command) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.email = command.email();
        this.address = command.address();
        return this;
    }
}
