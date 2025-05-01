package com.homeypark.web_service.user.application.internal.acl;

import com.homeypark.web_service.user.domain.model.queries.GetProfileByIdQuery;
import com.homeypark.web_service.user.domain.services.ProfileQueryService;
import com.homeypark.web_service.user.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;


@Service
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacadeImpl(ProfileQueryService profileQueryService) {
        this.profileQueryService = profileQueryService;
    }

    @Override
    public boolean checkUserExistById(Long profileId) {
        var getUserByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getUserByIdQuery);
        return profile.isPresent();
    }
}
