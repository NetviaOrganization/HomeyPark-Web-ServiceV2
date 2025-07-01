package com.homeypark.web_service.reservations.application.internal.commandservices;

import com.homeypark.web_service.reservations.application.internal.outboundservices.acl.ExternalProfileService;
import com.homeypark.web_service.reservations.domain.model.commands.CreateReviewCommand;
import com.homeypark.web_service.reservations.domain.model.commands.DeleteReviewCommand;
import com.homeypark.web_service.reservations.domain.model.commands.UpdateReviewCommand;
import com.homeypark.web_service.reservations.domain.model.entities.Review;
import com.homeypark.web_service.reservations.domain.model.exceptions.*;
import com.homeypark.web_service.reservations.domain.services.ReviewCommandService;
import com.homeypark.web_service.profiles.domain.model.exceptions.ProfileNotFoundException;
import com.homeypark.web_service.reservations.infrastructure.persistence.jpa.repositories.ReservationRepository;
import com.homeypark.web_service.reservations.infrastructure.persistence.jpa.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final ExternalProfileService externalProfileService;
    
    @Transactional
    @Override
    public Optional<Review> handle(CreateReviewCommand command) {
        // Verificar que la reserva existe
        var reservation = reservationRepository.findById(command.reservationId())
            .orElseThrow(ReservationNotFoundException::new);
        
        // Verificar que la reserva está completada
        if (!reservation.canBeReviewed()) {
            throw new InvalidReviewStateException("Cannot create review for reservation that is not completed");
        }
        
        // Verificar que no existe ya una reseña para esta reserva
        if (reviewRepository.existsByReservationId(command.reservationId())) {
            throw new ReviewAlreadyExistsException();
        }
        
        // Validar que los perfiles existen
        if (command.reviewerGuestId() != null && !externalProfileService.checkProfileExistById(command.reviewerGuestId())) {
            throw new ProfileNotFoundException();
        }
        if (command.reviewerHostId() != null && !externalProfileService.checkProfileExistById(command.reviewerHostId())) {
            throw new ProfileNotFoundException();
        }
        if (command.reviewedGuestId() != null && !externalProfileService.checkProfileExistById(command.reviewedGuestId())) {
            throw new ProfileNotFoundException();
        }
        if (command.reviewedHostId() != null && !externalProfileService.checkProfileExistById(command.reviewedHostId())) {
            throw new ProfileNotFoundException();
        }
        
        // Crear la reseña
        var review = new Review(command);
        review.setReservation(reservation);
        
        try {
            var savedReview = reviewRepository.save(review);
            return Optional.of(savedReview);
        } catch (Exception e) {
            System.err.println("Error saving review: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    @Transactional
    @Override
    public Optional<Review> handle(UpdateReviewCommand command) {
        var review = reviewRepository.findById(command.reviewId())
            .orElseThrow(ReviewNotFoundException::new);
        
        try {
            var updatedReview = reviewRepository.save(review.updateReview(command));
            return Optional.of(updatedReview);
        } catch (Exception e) {
            System.err.println("Error updating review: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    @Override
    public void handle(DeleteReviewCommand command) {
        if (!reviewRepository.existsById(command.reviewId())) {
            throw new ReviewNotFoundException();
        }
        
        try {
            reviewRepository.deleteById(command.reviewId());
        } catch (Exception e) {
            System.err.println("Error deleting review: " + e.getMessage());
            throw new RuntimeException("Failed to delete review");
        }
    }
}
