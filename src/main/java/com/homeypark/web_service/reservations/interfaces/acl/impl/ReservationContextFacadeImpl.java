package com.homeypark.web_service.reservations.interfaces.acl.impl;

import com.homeypark.web_service.reservations.domain.model.queries.GetAverageRatingByGuestIdQuery;
import com.homeypark.web_service.reservations.domain.model.queries.GetAverageRatingByHostIdQuery;
import com.homeypark.web_service.reservations.domain.model.queries.GetReviewsByGuestIdQuery;
import com.homeypark.web_service.reservations.domain.model.queries.GetReviewsByHostIdQuery;
import com.homeypark.web_service.reservations.domain.services.ReviewQueryService;
import com.homeypark.web_service.reservations.interfaces.acl.ReservationContextFacade;
import com.homeypark.web_service.reservations.interfaces.rest.resources.UserReputationResource;
import org.springframework.stereotype.Service;

@Service
public class ReservationContextFacadeImpl implements ReservationContextFacade {
    
    private final ReviewQueryService reviewQueryService;
    
    public ReservationContextFacadeImpl(ReviewQueryService reviewQueryService) {
        this.reviewQueryService = reviewQueryService;
    }
    
    @Override
    public UserReputationResource getUserReputationByUserId(Long userId) {
        var hostAverage = getAverageRatingAsHost(userId);
        var guestAverage = getAverageRatingAsGuest(userId);
        var hostReviewCount = getReviewCountAsHost(userId);
        var guestReviewCount = getReviewCountAsGuest(userId);
        
        return new UserReputationResource(
            userId,
            hostAverage,
            guestAverage,
            hostReviewCount,
            guestReviewCount
        );
    }
    
    @Override
    public Double getAverageRatingAsHost(Long userId) {
        var query = new GetAverageRatingByHostIdQuery(userId);
        return reviewQueryService.handle(query);
    }
    
    @Override
    public Double getAverageRatingAsGuest(Long userId) {
        var query = new GetAverageRatingByGuestIdQuery(userId);
        return reviewQueryService.handle(query);
    }
    
    @Override
    public Integer getReviewCountAsHost(Long userId) {
        var query = new GetReviewsByHostIdQuery(userId);
        return reviewQueryService.handle(query).size();
    }
    
    @Override
    public Integer getReviewCountAsGuest(Long userId) {
        var query = new GetReviewsByGuestIdQuery(userId);
        return reviewQueryService.handle(query).size();
    }
}
