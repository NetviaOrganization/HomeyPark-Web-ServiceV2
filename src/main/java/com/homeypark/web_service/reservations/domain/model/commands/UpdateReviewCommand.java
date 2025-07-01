package com.homeypark.web_service.reservations.domain.model.commands;

public record UpdateReviewCommand(
    Long reviewId,
    Integer stars,
    String comment
) {}
