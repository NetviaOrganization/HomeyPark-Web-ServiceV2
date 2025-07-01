package com.homeypark.web_service.reservations.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.homeypark.web_service.reservations.domain.model.aggregates.Reservation;
import com.homeypark.web_service.reservations.domain.model.commands.CreateReviewCommand;
import com.homeypark.web_service.reservations.domain.model.commands.UpdateReviewCommand;
import com.homeypark.web_service.reservations.domain.model.valueobject.GuestId;
import com.homeypark.web_service.reservations.domain.model.valueobject.HostId;
import com.homeypark.web_service.reservations.domain.model.valueobject.Rating;
import com.homeypark.web_service.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review extends AuditableModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    @JsonBackReference
    private Reservation reservation;

    @Embedded
    private Rating rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "guestId", column = @Column(name = "reviewer_guest_id"))
    })
    private GuestId reviewerGuestId;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "hostId", column = @Column(name = "reviewer_host_id"))
    })
    private HostId reviewerHostId;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "guestId", column = @Column(name = "reviewed_guest_id"))
    })
    private GuestId reviewedGuestId;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "hostId", column = @Column(name = "reviewed_host_id"))
    })
    private HostId reviewedHostId;

    // Constructor para crear reseña desde comando
    public Review(CreateReviewCommand command) {
        this.rating = new Rating(command.stars());
        this.comment = command.comment();
        this.reviewerGuestId = command.reviewerGuestId() != null ? new GuestId(command.reviewerGuestId()) : null;
        this.reviewerHostId = command.reviewerHostId() != null ? new HostId(command.reviewerHostId()) : null;
        this.reviewedGuestId = command.reviewedGuestId() != null ? new GuestId(command.reviewedGuestId()) : null;
        this.reviewedHostId = command.reviewedHostId() != null ? new HostId(command.reviewedHostId()) : null;
    }

    // Método para actualizar reseña
    public Review updateReview(UpdateReviewCommand command) {
        this.rating = new Rating(command.stars());
        this.comment = command.comment();
        return this;
    }

    // Método para verificar si el usuario puede crear esta reseña
    public boolean canReviewBy(Long userId, boolean isHost) {
        if (isHost) {
            return this.reviewerHostId != null && this.reviewerHostId.hostId().equals(userId);
        } else {
            return this.reviewerGuestId != null && this.reviewerGuestId.guestId().equals(userId);
        }
    }
}
