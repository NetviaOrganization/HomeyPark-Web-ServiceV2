package com.homeypark.web_service.reservations.application.internal.queryservices;

import com.homeypark.web_service.reservations.domain.model.entities.Review;
import com.homeypark.web_service.reservations.domain.model.queries.*;
import com.homeypark.web_service.reservations.domain.model.valueobject.GuestId;
import com.homeypark.web_service.reservations.domain.model.valueobject.HostId;
import com.homeypark.web_service.reservations.domain.services.ReviewQueryService;
import com.homeypark.web_service.reservations.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("reviewQueryServiceImpl")
public class ReviewQueryServiceImplementation implements ReviewQueryService {
    
    private final ReviewRepository reviewRepository;
    
    public ReviewQueryServiceImplementation(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    
    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return reviewRepository.findById(query.reviewId());
    }
    
    @Override
    public List<Review> handle(GetReviewsByHostIdQuery query) {
        var hostId = new HostId(query.hostId());
        return reviewRepository.findByReviewedHostId(hostId);
    }
    
    @Override
    public List<Review> handle(GetReviewsByGuestIdQuery query) {
        var guestId = new GuestId(query.guestId());
        return reviewRepository.findByReviewedGuestId(guestId);
    }
    
    @Override
    public Optional<Review> handle(GetReviewByReservationIdQuery query) {
        return reviewRepository.findByReservationId(query.reservationId());
    }
    
    @Override
    public Double handle(GetAverageRatingByHostIdQuery query) {
        var hostId = new HostId(query.hostId());
        var average = reviewRepository.findAverageRatingByHostId(hostId);
        return average != null ? average : 0.0;
    }
    
    @Override
    public Double handle(GetAverageRatingByGuestIdQuery query) {
        var guestId = new GuestId(query.guestId());
        var average = reviewRepository.findAverageRatingByGuestId(guestId);
        return average != null ? average : 0.0;
    }
}
