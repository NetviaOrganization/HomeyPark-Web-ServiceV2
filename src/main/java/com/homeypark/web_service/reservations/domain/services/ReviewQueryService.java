package com.homeypark.web_service.reservations.domain.services;

import com.homeypark.web_service.reservations.domain.model.entities.Review;
import com.homeypark.web_service.reservations.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    Optional<Review> handle(GetReviewByIdQuery query);
    List<Review> handle(GetReviewsByHostIdQuery query);
    List<Review> handle(GetReviewsByGuestIdQuery query);
    Optional<Review> handle(GetReviewByReservationIdQuery query);
    Double handle(GetAverageRatingByHostIdQuery query);
    Double handle(GetAverageRatingByGuestIdQuery query);
}
