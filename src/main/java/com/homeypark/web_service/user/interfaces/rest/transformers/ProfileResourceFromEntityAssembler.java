package com.homeypark.web_service.user.interfaces.rest.transformers;

import com.homeypark.web_service.user.domain.model.aggregates.Profile;
import com.homeypark.web_service.user.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getIdCardType().name(),
                entity.getIdCardNumber(),
                entity.getType().name()
        );
    }
}
