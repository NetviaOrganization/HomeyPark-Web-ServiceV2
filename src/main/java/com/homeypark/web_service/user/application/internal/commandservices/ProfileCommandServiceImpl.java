package com.homeypark.web_service.user.application.internal.commandservices;


import com.homeypark.web_service.user.domain.model.aggregates.Profile;
import com.homeypark.web_service.user.domain.model.commands.CreateProfileCommand;
import com.homeypark.web_service.user.domain.model.commands.DeleteProfileCommand;
import com.homeypark.web_service.user.domain.model.commands.UpdateProfileCommand;
import com.homeypark.web_service.user.domain.services.ProfileCommandService;
import com.homeypark.web_service.user.infrastructure.repositories.jpa.ProfileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        Profile user = new Profile(command);
        user.setDateCreated(LocalDateTime.now());
        try {
            var response = profileRepository.save(user);

            return Optional.of(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command) {
        var result = profileRepository.findById(command.profileId());
        if (result.isEmpty())
            throw new IllegalArgumentException("User does not exist");
        var userToUpdate = result.get();
        try{
            var updatedUser= profileRepository.save(userToUpdate.updatedProfile(command));
            return Optional.of(updatedUser);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating user: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteProfileCommand command){
        if (!profileRepository.existsById(command.profileId())) throw new IllegalArgumentException("User does not exist");
        profileRepository.deleteById(command.profileId());
        System.out.println("User Delete");
    }

}
