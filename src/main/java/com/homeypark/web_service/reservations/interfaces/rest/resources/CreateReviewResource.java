package com.homeypark.web_service.reservations.interfaces.rest.resources;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateReviewResource(
    @NotNull(message = "Reservation ID is required")
    Long reservationId,
    
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1 star")
    @Max(value = 5, message = "Rating must be at most 5 stars")
    Integer stars,
    
    @Size(max = 1000, message = "Comment cannot exceed 1000 characters")
    String comment,
    
    Long reviewerGuestId,
    Long reviewerHostId,
    Long reviewedGuestId,
    Long reviewedHostId
) {}
