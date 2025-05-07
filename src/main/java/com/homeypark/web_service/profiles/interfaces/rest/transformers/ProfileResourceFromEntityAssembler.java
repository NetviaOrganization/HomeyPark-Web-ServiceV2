package com.homeypark.web_service.profiles.interfaces.rest.transformers;

import com.homeypark.web_service.profiles.domain.model.aggregates.Profile;
import com.homeypark.web_service.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getAddress(),
                entity.getUserId().userIdAsPrimitive()
        );
    }
}
