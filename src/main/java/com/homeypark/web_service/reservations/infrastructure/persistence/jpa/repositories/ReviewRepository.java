package com.homeypark.web_service.reservations.infrastructure.persistence.jpa.repositories;

import com.homeypark.web_service.reservations.domain.model.entities.Review;
import com.homeypark.web_service.reservations.domain.model.valueobject.GuestId;
import com.homeypark.web_service.reservations.domain.model.valueobject.HostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    Optional<Review> findByReservationId(Long reservationId);
    
    List<Review> findByReviewedHostId(HostId hostId);
    
    List<Review> findByReviewedGuestId(GuestId guestId);
    
    @Query("SELECT AVG(r.rating.stars) FROM Review r WHERE r.reviewedHostId = :hostId")
    Double findAverageRatingByHostId(@Param("hostId") HostId hostId);
    
    @Query("SELECT AVG(r.rating.stars) FROM Review r WHERE r.reviewedGuestId = :guestId")
    Double findAverageRatingByGuestId(@Param("guestId") GuestId guestId);
    
    @Query("SELECT COUNT(r) FROM Review r WHERE r.reviewedHostId = :hostId")
    Long countReviewsByHostId(@Param("hostId") HostId hostId);
    
    @Query("SELECT COUNT(r) FROM Review r WHERE r.reviewedGuestId = :guestId")
    Long countReviewsByGuestId(@Param("guestId") GuestId guestId);
    
    boolean existsByReservationId(Long reservationId);
}
