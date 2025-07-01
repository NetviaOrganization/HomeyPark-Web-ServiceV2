package com.homeypark.web_service.reservations.interfaces.rest.transformers;

import com.homeypark.web_service.reservations.domain.model.commands.UpdateReviewCommand;
import com.homeypark.web_service.reservations.interfaces.rest.resources.UpdateReviewResource;

public class UpdateReviewCommandFromResourceAssembler {
    
    public static UpdateReviewCommand toCommandFromResource(Long reviewId, UpdateReviewResource resource) {
        return new UpdateReviewCommand(
            reviewId,
            resource.stars(),
            resource.comment()
        );
    }
}
