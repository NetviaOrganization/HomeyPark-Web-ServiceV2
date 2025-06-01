package com.homeypark.web_service.parkings.interfaces.rest.transform;

import com.homeypark.web_service.parkings.domain.model.commands.UpdateScheduleCommand;
import com.homeypark.web_service.parkings.interfaces.rest.resources.UpdateScheduleResource;

public class UpdateScheduleCommandFromResourceAssembler {
    public static UpdateScheduleCommand toCommandFromResource(Long id, UpdateScheduleResource resource) {
        return new UpdateScheduleCommand(
                id,
                resource.day(),
                resource.startTime(),
                resource.endTime());
    }
}
