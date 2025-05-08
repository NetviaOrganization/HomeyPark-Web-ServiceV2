package com.homeypark.web_service.profiles.domain.model.aggregates;

import com.homeypark.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.homeypark.web_service.profiles.domain.model.commands.UpdateProfileCommand;
import com.homeypark.web_service.profiles.domain.model.valueobject.UserId;
import com.homeypark.web_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "profiles")
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    private String name;
    private String lastName;
    private String address;

    @Embedded
    private UserId userId;

    public Profile(String lastName, String name) {
        this.lastName = lastName;
        this.name = name;
    }

    public Profile(CreateProfileCommand command) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.address = command.address();
        this.userId = new UserId(command.userId());
    }
    public Profile updatedProfile(UpdateProfileCommand command) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.address = command.address();
        return this;
    }
}
