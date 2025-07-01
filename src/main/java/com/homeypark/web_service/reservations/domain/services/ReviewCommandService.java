package com.homeypark.web_service.reservations.domain.services;

import com.homeypark.web_service.reservations.domain.model.commands.CreateReviewCommand;
import com.homeypark.web_service.reservations.domain.model.commands.DeleteReviewCommand;
import com.homeypark.web_service.reservations.domain.model.commands.UpdateReviewCommand;
import com.homeypark.web_service.reservations.domain.model.entities.Review;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Review> handle(CreateReviewCommand command);
    Optional<Review> handle(UpdateReviewCommand command);
    void handle(DeleteReviewCommand command);
}
