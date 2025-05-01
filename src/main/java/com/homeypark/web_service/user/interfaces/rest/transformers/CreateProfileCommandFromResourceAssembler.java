package com.homeypark.web_service.user.interfaces.rest.transformers;


import com.homeypark.web_service.user.domain.model.commands.CreateProfileCommand;
import com.homeypark.web_service.user.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.name(), resource.lastName(), resource.address(), resource.email(),resource.idCardType(),resource.idCardNumber(), resource.type());
    }
}
