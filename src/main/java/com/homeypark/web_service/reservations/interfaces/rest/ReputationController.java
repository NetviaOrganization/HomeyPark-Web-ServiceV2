package com.homeypark.web_service.reservations.interfaces.rest;

import com.homeypark.web_service.reservations.domain.model.queries.GetAverageRatingByGuestIdQuery;
import com.homeypark.web_service.reservations.domain.model.queries.GetAverageRatingByHostIdQuery;
import com.homeypark.web_service.reservations.domain.model.queries.GetReviewsByGuestIdQuery;
import com.homeypark.web_service.reservations.domain.model.queries.GetReviewsByHostIdQuery;
import com.homeypark.web_service.reservations.domain.services.ReviewQueryService;
import com.homeypark.web_service.reservations.interfaces.rest.resources.UserReputationResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reputation")
@Tag(name = "Reputation", description = "User Reputation Endpoints")
public class ReputationController {
    
    private final ReviewQueryService reviewQueryService;
    
    public ReputationController(ReviewQueryService reviewQueryService) {
        this.reviewQueryService = reviewQueryService;
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserReputationResource> getUserReputation(@PathVariable Long userId) {
        var getHostAverageRatingQuery = new GetAverageRatingByHostIdQuery(userId);
        var getGuestAverageRatingQuery = new GetAverageRatingByGuestIdQuery(userId);
        var getHostReviewsQuery = new GetReviewsByHostIdQuery(userId);
        var getGuestReviewsQuery = new GetReviewsByGuestIdQuery(userId);
        
        var hostAverageRating = reviewQueryService.handle(getHostAverageRatingQuery);
        var guestAverageRating = reviewQueryService.handle(getGuestAverageRatingQuery);
        var hostReviews = reviewQueryService.handle(getHostReviewsQuery);
        var guestReviews = reviewQueryService.handle(getGuestReviewsQuery);
        
        var reputation = new UserReputationResource(
            userId,
            hostAverageRating,
            guestAverageRating,
            hostReviews.size(),
            guestReviews.size()
        );
        
        return new ResponseEntity<>(reputation, HttpStatus.OK);
    }
    
    @GetMapping("/host/{hostId}")
    public ResponseEntity<Double> getHostAverageRating(@PathVariable Long hostId) {
        var getAverageRatingQuery = new GetAverageRatingByHostIdQuery(hostId);
        var averageRating = reviewQueryService.handle(getAverageRatingQuery);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }
    
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<Double> getGuestAverageRating(@PathVariable Long guestId) {
        var getAverageRatingQuery = new GetAverageRatingByGuestIdQuery(guestId);
        var averageRating = reviewQueryService.handle(getAverageRatingQuery);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }
}
