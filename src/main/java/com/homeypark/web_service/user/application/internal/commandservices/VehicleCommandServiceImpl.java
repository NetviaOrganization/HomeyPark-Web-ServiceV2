package com.homeypark.web_service.user.application.internal.commandservices;

import com.homeypark.web_service.user.domain.model.aggregates.Profile;
import com.homeypark.web_service.user.domain.model.commands.CreateVehicleCommand;
import com.homeypark.web_service.user.domain.model.commands.DeleteVehicleCommand;
import com.homeypark.web_service.user.domain.model.commands.UpdateVehicleCommand;
import com.homeypark.web_service.user.domain.model.entities.Vehicle;
import com.homeypark.web_service.user.domain.services.VehicleCommandService;
import com.homeypark.web_service.user.infrastructure.repositories.jpa.ProfileRepository;
import com.homeypark.web_service.user.infrastructure.repositories.jpa.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {

    private final VehicleRepository vehicleRepository;
    private final ProfileRepository profileRepository;

    public VehicleCommandServiceImpl(VehicleRepository vehicleRepository, ProfileRepository profileRepository) {
        this.vehicleRepository = vehicleRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Vehicle> handle(CreateVehicleCommand command) {
        Optional<Profile> user = profileRepository.findById(command.userId());

        if (user.isPresent()) {
            // Crear un nuevo vehículo usando el constructor que acepta CreateVehicleCommand
            Vehicle vehicle = new Vehicle(command, user.get());
            var response = vehicleRepository.save(vehicle);
            return Optional.of(response);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Vehicle> handle(UpdateVehicleCommand command) {
        var result = vehicleRepository.findById(command.vehicleId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Vehicle does not exist");
        var vehicleToUpdate = result.get();
        try{
            var updatedVehicle= vehicleRepository.save(vehicleToUpdate.updatedVehicle(command));
            return Optional.of(updatedVehicle);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating vehicle: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteVehicleCommand command){
        if (!vehicleRepository.existsById(command.vehicleId())) throw new IllegalArgumentException("Vehicle does not exist");
        vehicleRepository.deleteById(command.vehicleId());
        System.out.println("Vehicle Delete");
    }


}