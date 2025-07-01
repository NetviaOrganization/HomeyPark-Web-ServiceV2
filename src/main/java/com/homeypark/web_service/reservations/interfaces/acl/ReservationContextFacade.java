package com.homeypark.web_service.reservations.interfaces.acl;

import com.homeypark.web_service.reservations.interfaces.rest.resources.UserReputationResource;

public interface ReservationContextFacade {
    UserReputationResource getUserReputationByUserId(Long userId);
    Double getAverageRatingAsHost(Long userId);
    Double getAverageRatingAsGuest(Long userId);
    Integer getReviewCountAsHost(Long userId);
    Integer getReviewCountAsGuest(Long userId);
}
