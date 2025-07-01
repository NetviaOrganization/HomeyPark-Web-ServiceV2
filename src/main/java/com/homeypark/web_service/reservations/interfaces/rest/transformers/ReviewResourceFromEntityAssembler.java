package com.homeypark.web_service.reservations.interfaces.rest.transformers;

import com.homeypark.web_service.reservations.domain.model.entities.Review;
import com.homeypark.web_service.reservations.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    
    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(
            entity.getId(),
            entity.getReservation().getId(),
            entity.getRating().stars(),
            entity.getComment(),
            entity.getReviewerGuestId() != null ? entity.getReviewerGuestId().guestId() : null,
            entity.getReviewerHostId() != null ? entity.getReviewerHostId().hostId() : null,
            entity.getReviewedGuestId() != null ? entity.getReviewedGuestId().guestId() : null,
            entity.getReviewedHostId() != null ? entity.getReviewedHostId().hostId() : null,
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
