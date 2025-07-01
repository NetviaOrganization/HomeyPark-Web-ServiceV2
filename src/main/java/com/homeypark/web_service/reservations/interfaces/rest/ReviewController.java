package com.homeypark.web_service.reservations.interfaces.rest;

import com.homeypark.web_service.reservations.domain.model.commands.DeleteReviewCommand;
import com.homeypark.web_service.reservations.domain.model.queries.*;
import com.homeypark.web_service.reservations.domain.services.ReviewCommandService;
import com.homeypark.web_service.reservations.domain.services.ReviewQueryService;
import com.homeypark.web_service.reservations.interfaces.rest.resources.*;
import com.homeypark.web_service.reservations.interfaces.rest.transformers.CreateReviewCommandFromResourceAssembler;
import com.homeypark.web_service.reservations.interfaces.rest.transformers.ReviewResourceFromEntityAssembler;
import com.homeypark.web_service.reservations.interfaces.rest.transformers.UpdateReviewCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
@Tag(name = "Reviews", description = "Review Management Endpoints")
public class ReviewController {
    
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;
    
    public ReviewController(ReviewCommandService reviewCommandService, ReviewQueryService reviewQueryService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }
    
    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@Valid @RequestBody CreateReviewResource createReviewResource) {
        var createReviewCommand = CreateReviewCommandFromResourceAssembler.toCommandFromResource(createReviewResource);
        var review = reviewCommandService.handle(createReviewCommand)
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity);
        return review.map(r -> new ResponseEntity<>(r, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResource> getReviewById(@PathVariable("id") Long id) {
        var getReviewByIdQuery = new GetReviewByIdQuery(id);
        var review = reviewQueryService.handle(getReviewByIdQuery)
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity);
        return review.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<ReviewResource> getReviewByReservationId(@PathVariable Long reservationId) {
        var getReviewByReservationIdQuery = new GetReviewByReservationIdQuery(reservationId);
        var review = reviewQueryService.handle(getReviewByReservationIdQuery)
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity);
        return review.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<ReviewResource>> getReviewsByHostId(@PathVariable Long hostId) {
        var getReviewsByHostIdQuery = new GetReviewsByHostIdQuery(hostId);
        var reviews = reviewQueryService.handle(getReviewsByHostIdQuery)
                .stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<ReviewResource>> getReviewsByGuestId(@PathVariable Long guestId) {
        var getReviewsByGuestIdQuery = new GetReviewsByGuestIdQuery(guestId);
        var reviews = reviewQueryService.handle(getReviewsByGuestIdQuery)
                .stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResource> updateReview(@PathVariable Long id, @Valid @RequestBody UpdateReviewResource updateReviewResource) {
        var updateReviewCommand = UpdateReviewCommandFromResourceAssembler.toCommandFromResource(id, updateReviewResource);
        var updatedReview = reviewCommandService.handle(updateReviewCommand)
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity);
        return updatedReview.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        var deleteReviewCommand = new DeleteReviewCommand(id);
        reviewCommandService.handle(deleteReviewCommand);
        return ResponseEntity.noContent().build();
    }
}
