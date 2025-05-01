package com.homeypark.web_service.user.interfaces.rest.transformers;

import com.homeypark.web_service.user.domain.model.commands.UpdateProfileCommand;
import com.homeypark.web_service.user.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResource {
    public static UpdateProfileCommand toCommandFromResource(Long userId, UpdateProfileResource resource) {
        return new UpdateProfileCommand(userId, resource.name(), resource.lastName(), resource.address(), resource.email(), resource.idCardType(), resource.idCardNumber());
    }
}
