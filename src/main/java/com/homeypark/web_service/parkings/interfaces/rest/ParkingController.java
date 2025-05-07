package com.homeypark.web_service.parkings.interfaces.rest;

import com.homeypark.web_service.parkings.domain.model.aggregates.Parking;
import com.homeypark.web_service.parkings.domain.model.commands.DeleteParkingCommand;
import com.homeypark.web_service.parkings.domain.model.queries.GetAllParkingQuery;
import com.homeypark.web_service.parkings.domain.model.queries.GetParkingByIdQuery;
import com.homeypark.web_service.parkings.domain.model.queries.GetParkingListByProfileId;
import com.homeypark.web_service.parkings.domain.model.queries.GetParkingsByNearLatLngQuery;
import com.homeypark.web_service.parkings.domain.services.ParkingCommandService;
import com.homeypark.web_service.parkings.domain.services.ParkingQueryService;
import com.homeypark.web_service.parkings.interfaces.rest.resources.CreateParkingResource;
import com.homeypark.web_service.parkings.interfaces.rest.resources.UpdateParkingResource;
import com.homeypark.web_service.parkings.interfaces.rest.transform.CreateParkingCommandFromResourceAssembler;
import com.homeypark.web_service.parkings.interfaces.rest.transform.UpdateParkingCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/parking")
@Tag(name = "Parking", description = "Parking Management Endpoints")
public class ParkingController {
    private final ParkingCommandService parkingCommandService;
    private final ParkingQueryService parkingQueryService;

    public ParkingController(ParkingCommandService parkingCommandService, ParkingQueryService parkingQueryService) {
        this.parkingCommandService = parkingCommandService;
        this.parkingQueryService = parkingQueryService;
    }

    @GetMapping
    public ResponseEntity<List<Parking>> getAllParking() {
        var getAllParkingQuery = new GetAllParkingQuery();
        var parkingList = parkingQueryService.handle(getAllParkingQuery);

        return new ResponseEntity<>(parkingList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Parking> createParking(@RequestBody CreateParkingResource createParkingResource) {
        var createParkingCommand = CreateParkingCommandFromResourceAssembler.toCommandFromResource(createParkingResource);
        var parking = parkingCommandService.handle(createParkingCommand);
        return parking.map(p -> new ResponseEntity<>(p, HttpStatus.CREATED)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parking> updateParking(@PathVariable Long id, @RequestBody UpdateParkingResource updateParkingResource){

        var updateParkingCommand = UpdateParkingCommandFromResourceAssembler.toCommandFromResource(id, updateParkingResource);

        var updatedParking = parkingCommandService.handle(updateParkingCommand);

        return updatedParking.map(p -> new ResponseEntity<>(p, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteParking(@PathVariable Long id){
        var deleteParkingCommand = new DeleteParkingCommand(id);
        parkingCommandService.handle(deleteParkingCommand);
        return ResponseEntity.ok("Parking with given id succesfully deleted");
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Parking> getParkingDetailsById(@PathVariable Long id) {
        GetParkingByIdQuery query = new GetParkingByIdQuery(id);
        Optional<Parking> parking = parkingQueryService.handle(query);

        if (parking.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(parking.get());
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<List<Parking>> getParkingListByProfileId(@PathVariable Long id) {
        GetParkingListByProfileId query = new GetParkingListByProfileId(id);

        List<Parking> parkingList = parkingQueryService.handle(query);

        return ResponseEntity.ok(parkingList);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<Parking>> getNearbyLocation(@RequestParam double lat, @RequestParam double lng){
        var getParkingsByNearLatLngQuery = new GetParkingsByNearLatLngQuery(lat, lng);

        List<Parking> parkingList = parkingQueryService.handle(getParkingsByNearLatLngQuery);

        return new ResponseEntity<>(parkingList, HttpStatus.OK);
    }
}
