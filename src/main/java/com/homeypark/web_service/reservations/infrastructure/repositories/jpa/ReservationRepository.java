package com.homeypark.web_service.reservations.infrastructure.repositories.jpa;

import com.homeypark.web_service.reservations.domain.model.aggregates.Reservation;
import com.homeypark.web_service.reservations.domain.model.valueobject.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatus(Status status);
    List<Reservation> findByStatusIn(List<Status> statusList);
    List<Reservation> findByHostId(Long hostId);
    List<Reservation> findByGuestId(Long guestId);
}
