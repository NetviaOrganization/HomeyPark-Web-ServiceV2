package com.homeypark.web_service.profiles.interfaces.rest.transformers;

import com.homeypark.web_service.profiles.domain.model.aggregates.Profile;
import com.homeypark.web_service.profiles.interfaces.rest.resources.ProfileWithReputationResource;
import com.homeypark.web_service.reservations.interfaces.rest.resources.UserReputationResource;

public class ProfileWithReputationResourceFromEntityAssembler {
    
    public static ProfileWithReputationResource toResourceFromEntity(Profile profile, UserReputationResource reputation) {
        return new ProfileWithReputationResource(
            profile.getId(),
            profile.getFirstName(),
            profile.getLastName(),
            profile.getBirthDate(),
            profile.getUserId().userId(),
            reputation.averageRatingAsHost(),
            reputation.reviewCountAsHost(),
            reputation.averageRatingAsGuest(),
            reputation.reviewCountAsGuest(),
            profile.getCreatedAt(),
            profile.getUpdatedAt()
        );
    }
}
