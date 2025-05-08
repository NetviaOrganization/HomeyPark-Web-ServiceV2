package com.homeypark.web_service.reservations.application.internal.outboundservices.acl;

import com.homeypark.web_service.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service("reservationExternalProfileService")
public class ExternalProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public boolean checkProfileExistById(Long profileId) {
        return profilesContextFacade.checkProfileExistById(profileId);
    }
}
