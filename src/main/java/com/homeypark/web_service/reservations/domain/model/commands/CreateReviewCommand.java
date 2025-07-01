package com.homeypark.web_service.reservations.domain.model.commands;

public record CreateReviewCommand(
    Long reservationId,
    Integer stars,
    String comment,
    Long reviewerGuestId,
    Long reviewerHostId,
    Long reviewedGuestId,
    Long reviewedHostId
) {}
