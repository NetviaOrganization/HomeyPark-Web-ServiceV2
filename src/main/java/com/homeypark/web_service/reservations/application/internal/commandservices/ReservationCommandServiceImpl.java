package com.homeypark.web_service.reservations.application.internal.commandservices;

import com.homeypark.web_service.reservations.application.internal.outboundservices.acl.ExternalParkingService;
import com.homeypark.web_service.reservations.application.internal.outboundservices.acl.ExternalProfileService;
import com.homeypark.web_service.reservations.application.internal.outboundservices.acl.ExternalScheduleService;
import com.homeypark.web_service.reservations.application.internal.outboundservices.acl.ExternalVehicleService;
import com.homeypark.web_service.reservations.domain.model.commands.CreateReservationCommand;
import com.homeypark.web_service.reservations.domain.model.commands.UpdateReservationCommand;
import com.homeypark.web_service.reservations.domain.model.commands.UpdateStatusCommand;
import com.homeypark.web_service.reservations.domain.model.aggregates.Reservation;
import com.homeypark.web_service.reservations.domain.model.valueobject.Status;
import com.homeypark.web_service.reservations.domain.services.ReservationCommandService;
import com.homeypark.web_service.reservations.infrastructure.external.ImageUploadService;
import com.homeypark.web_service.reservations.infrastructure.external.ImgbbResponse;
import com.homeypark.web_service.reservations.infrastructure.repositories.jpa.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {
    private final ReservationRepository reservationRepository;
    private final ExternalProfileService externalProfileService;
    private final ExternalVehicleService externalVehicleService;
    private final ExternalParkingService externalParkingService;
    private final ImageUploadService imageUploadService;
    private final ExternalScheduleService externalScheduleService;

    @Transactional
    @Override
    public Optional<Reservation> handle(CreateReservationCommand command, MultipartFile file) {
        if (!externalProfileService.checkProfileExistById(command.guestId()) || !externalProfileService.checkProfileExistById(command.hostId())) {
            throw new IllegalArgumentException("Guest or Host not found");
        }
        if (file.isEmpty()) throw new IllegalArgumentException("File is empty");
        if (!externalVehicleService.checkVehicleExistById(command.guestId())) throw new IllegalArgumentException("Vehicle not found");
        if (!externalParkingService.checkParkingExistById(command.guestId())) throw new IllegalArgumentException("Parking not found");

        if (!externalScheduleService.doesScheduleEncloseTimeRange(command.startTime().getDayOfWeek().name(), command.startTime().toLocalTime(), command.endTime().toLocalTime())) {
            throw new IllegalArgumentException("Schedule does not enclose the time range");
        }

        if (reservationRepository.existsByOverlap(command.startTime(), command.endTime())) {
            throw new IllegalArgumentException("Reservation time overlaps with an existing reservation");
        }


        var reservation = new Reservation(command);
        reservation.setStatus(Status.Pending);
        try {
            ImgbbResponse imgbbResponse = imageUploadService.uploadImage(file).block();

            if (imgbbResponse != null && imgbbResponse.success()) {
                reservation.setPaymentReceiptUrl(imgbbResponse.data().url());
                reservation.setPaymentReceiptDeleteUrl(imgbbResponse.data().deleteUrl());
            } else {
                System.err.println("Error al cargar la imagen o respuesta no exitosa de ImgBB.");
                return Optional.empty();
            }

            var response = reservationRepository.save(reservation);
            return Optional.of(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Reservation> handle(UpdateReservationCommand command) {
        var result = reservationRepository.findById(command.reservationId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Reservation does not exist");

        if (!externalScheduleService.doesScheduleEncloseTimeRange(command.startTime().getDayOfWeek().name(), command.startTime().toLocalTime(), command.endTime().toLocalTime())) {
            throw new IllegalArgumentException("Schedule does not enclose the time range");
        }

        if (reservationRepository.existsByOverlap(command.startTime(), command.endTime())) {
            throw new IllegalArgumentException("Reservation time overlaps with an existing reservation");
        }

        var reservationToUpdate = result.get();
        try{
            var updatedReservation= reservationRepository.save(reservationToUpdate.updatedReservation(command));
            return Optional.of(updatedReservation);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating reservation: " + e.getMessage());
        }
    }

    @Override
    public Optional<Reservation> handle(UpdateStatusCommand command) {
        var result = reservationRepository.findById(command.reservationId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Reservation does not exist");
        var statusToUpdate = result.get();
        try {
            var updatedStatus = reservationRepository.save(statusToUpdate.updatedStatus(command));
            return Optional.of(updatedStatus);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating status: "+ e.getMessage());
        }
    }
}
