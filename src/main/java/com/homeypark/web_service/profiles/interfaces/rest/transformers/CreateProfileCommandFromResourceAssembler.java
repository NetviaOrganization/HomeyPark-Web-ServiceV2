package com.homeypark.web_service.profiles.interfaces.rest.transformers;


import com.homeypark.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.homeypark.web_service.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.name(), resource.lastName(), resource.address(), resource.userId());
    }
}
