package com.homeypark.web_service.parkings.application.internal.outboundservices.acl;

import com.homeypark.web_service.user.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalProfileService {
    private final ProfilesContextFacade userContextFacade;

    public ExternalProfileService(ProfilesContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }
    public boolean checkProfileExistById(Long userId) {
        return userContextFacade.checkUserExistById(userId);
    }
}
