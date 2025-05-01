package com.homeypark.web_service.user.interfaces.rest;



import com.homeypark.web_service.user.domain.model.commands.DeleteProfileCommand;
import com.homeypark.web_service.user.domain.model.queries.GetAllProfilesQuery;
import com.homeypark.web_service.user.domain.model.queries.GetProfileByIdQuery;
import com.homeypark.web_service.user.domain.services.ProfileCommandService;
import com.homeypark.web_service.user.domain.services.ProfileQueryService;
import com.homeypark.web_service.user.interfaces.rest.resources.CreateProfileResource;
import com.homeypark.web_service.user.interfaces.rest.resources.ProfileResource;
import com.homeypark.web_service.user.interfaces.rest.resources.UpdateProfileResource;
import com.homeypark.web_service.user.interfaces.rest.transformers.CreateProfileCommandFromResourceAssembler;
import com.homeypark.web_service.user.interfaces.rest.transformers.ProfileResourceFromEntityAssembler;
import com.homeypark.web_service.user.interfaces.rest.transformers.UpdateProfileCommandFromResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileController {
    private final ProfileQueryService userQueryService;
    private final ProfileCommandService userCommandService;

    public ProfileController(ProfileQueryService userQueryService, ProfileCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllUsers() {

        var getAllUsersQuery = new GetAllProfilesQuery();
        var users = userQueryService.handle(getAllUsersQuery).stream().map(ProfileResourceFromEntityAssembler::toResourceFromEntity).toList();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProfileResource> getUserById(@PathVariable("id") Long id) {
        var getUserByIdQuery = new GetProfileByIdQuery(id);

        var user = userQueryService.handle(getUserByIdQuery).map(ProfileResourceFromEntityAssembler::toResourceFromEntity);

        return user.map(u -> new ResponseEntity<>(u, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createUser(@RequestBody CreateProfileResource createUserResource) {
        var createUserCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(createUserResource);

        var user = userCommandService.handle(createUserCommand).map(ProfileResourceFromEntityAssembler::toResourceFromEntity);

        return user.map(u -> new ResponseEntity<>(u, HttpStatus.CREATED)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResource> updateUser(@PathVariable Long id, @RequestBody UpdateProfileResource updateUserResource) {
        var updateUserCommand = UpdateProfileCommandFromResource.toCommandFromResource(id, updateUserResource);
        var updatedUser = userCommandService.handle(updateUserCommand).map(ProfileResourceFromEntityAssembler::toResourceFromEntity);
        return updatedUser.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        var deleteUserCommand = new DeleteProfileCommand(id);
        userCommandService.handle(deleteUserCommand);
        return ResponseEntity.noContent().build();
    }

}
