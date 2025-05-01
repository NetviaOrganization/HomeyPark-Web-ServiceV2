package com.homeypark.web_service.reservations.interfaces.rest;

import com.homeypark.web_service.reservations.domain.model.entities.Reservation;
import com.homeypark.web_service.reservations.domain.model.queries.*;
import com.homeypark.web_service.reservations.domain.services.ReservationCommandService;
import com.homeypark.web_service.reservations.domain.services.ReservationQueryService;
import com.homeypark.web_service.reservations.interfaces.rest.resources.CreateReservationResource;
import com.homeypark.web_service.reservations.interfaces.rest.resources.UpdateReservationResource;
import com.homeypark.web_service.reservations.interfaces.rest.resources.UpdateStatusResource;
import com.homeypark.web_service.reservations.interfaces.rest.transformers.CreateReservationCommandFromResourceAssembler;
import com.homeypark.web_service.reservations.interfaces.rest.transformers.UpdateReservationCommandFromResourceAssembler;
import com.homeypark.web_service.reservations.interfaces.rest.transformers.UpdateStatusCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {
    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;


    public ReservationController(ReservationCommandService reservationCommandService, ReservationQueryService reservationQueryService) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
     var getAllReservationsQuery = new GetAllReservationsQuery();
     var reservationList = reservationQueryService.handle(getAllReservationsQuery);
     return new ResponseEntity<>(reservationList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody CreateReservationResource createReservationResource) {
        var createReservationCommand = CreateReservationCommandFromResourceAssembler.toCommandFromResource(createReservationResource);

        var reservation = reservationCommandService.handle(createReservationCommand);

        return reservation.map(r -> new ResponseEntity<>(r, HttpStatus.CREATED)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody UpdateReservationResource updateReservationResource) {
        var updateReservationCommand = UpdateReservationCommandFromResourceAssembler.toCommandFromResource(id, updateReservationResource);
        var updatedReservation = reservationCommandService.handle(updateReservationCommand);
        return updatedReservation.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Reservation> updateReservationStatus(@PathVariable Long id, @RequestBody UpdateStatusResource updateStatusResource){
        var updateStatusCommand = UpdateStatusCommandFromResourceAssembler.toCommandFromResource(id, updateStatusResource);
        var updatedStatus = reservationCommandService.handle(updateStatusCommand);
        return updatedStatus.map(r -> new ResponseEntity<>(r,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        var getReservationByIdQuery = new GetReservationByIdQuery(id);

        var reservation = reservationQueryService.handle(getReservationByIdQuery);

        return reservation.map(r -> new ResponseEntity<>(r, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<Reservation>> getReservationsByHostId(@PathVariable Long hostId){
        var getReservationsByHostIdQuery = new GetReservationsByHostIdQuery(hostId);
        var reservationList = reservationQueryService.handle(getReservationsByHostIdQuery);
        return new ResponseEntity<>(reservationList,HttpStatus.OK);
    }
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<Reservation>> getReservationsByGuestId(@PathVariable Long guestId){
        var getReservationsByGuestIdQuery = new GetReservationsByGuestIdQuery(guestId);
        var reservationList = reservationQueryService.handle(getReservationsByGuestIdQuery);
        return new ResponseEntity<>(reservationList,HttpStatus.OK);
    }
    @GetMapping("/{id}/details")
    public ResponseEntity<Map<String, Object>> getReservationDetails(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/inProgress")
    public ResponseEntity<List<Reservation>> getInProgressReservation(){
        var getInProgressReservationQuery = new GetInProgressReservationQuery();
        var inProgressList = reservationQueryService.handle(getInProgressReservationQuery);
        return new ResponseEntity<>(inProgressList,HttpStatus.OK);
    }
    @GetMapping("/upComing")
    public ResponseEntity<List<Reservation>> getUpComingReservation(){
        var getUpComingReservationQuery = new GetUpComingReservationQuery();
        var upComingList = reservationQueryService.handle(getUpComingReservationQuery);
        return new ResponseEntity<>(upComingList,HttpStatus.OK);
    }
    @GetMapping("/past")
    public ResponseEntity<List<Reservation>> getPastReservations(){
        var getPastReservationQuery = new GetPastReservationQuery();
        var pastList = reservationQueryService.handle(getPastReservationQuery);
        return new ResponseEntity<>(pastList,HttpStatus.OK);
    }
}
