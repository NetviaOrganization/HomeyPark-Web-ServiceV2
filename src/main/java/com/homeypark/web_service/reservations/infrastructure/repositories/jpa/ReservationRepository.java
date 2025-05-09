package com.homeypark.web_service.reservations.infrastructure.repositories.jpa;

import com.homeypark.web_service.reservations.domain.model.aggregates.Reservation;
import com.homeypark.web_service.reservations.domain.model.valueobject.GuestId;
import com.homeypark.web_service.reservations.domain.model.valueobject.HostId;
import com.homeypark.web_service.reservations.domain.model.valueobject.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatus(Status status);
    List<Reservation> findByStatusIn(List<Status> statusList);
    List<Reservation> findByHostId(HostId hostId);
    List<Reservation> findByGuestId(GuestId guestId);
    @Query(
            "SELECT COUNT(r.id) > 0 " +
                    "FROM Reservation r " +
                    "WHERE r.startTime < :endTime " +
                    "  AND r.endTime > :startTime"
    )
    boolean existsByOverlap(
                             @Param("startTime") LocalDateTime startTime,
                             @Param("endTime")   LocalDateTime endTime
    );
}
