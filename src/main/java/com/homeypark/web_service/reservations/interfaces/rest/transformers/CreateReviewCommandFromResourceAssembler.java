package com.homeypark.web_service.reservations.interfaces.rest.transformers;

import com.homeypark.web_service.reservations.domain.model.commands.CreateReviewCommand;
import com.homeypark.web_service.reservations.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(
            resource.reservationId(),
            resource.stars(),
            resource.comment(),
            resource.reviewerGuestId(),
            resource.reviewerHostId(),
            resource.reviewedGuestId(),
            resource.reviewedHostId()
        );
    }
}
