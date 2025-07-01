package com.homeypark.web_service.core.entities.unit.tests;

import com.homeypark.web_service.reservations.domain.model.commands.CreateReviewCommand;
import com.homeypark.web_service.reservations.domain.model.commands.UpdateReviewCommand;
import com.homeypark.web_service.reservations.domain.model.entities.Review;
import com.homeypark.web_service.reservations.domain.model.valueobject.GuestId;
import com.homeypark.web_service.reservations.domain.model.valueobject.HostId;
import com.homeypark.web_service.reservations.domain.model.valueobject.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReviewTest {
    
    private CreateReviewCommand createCommand;
    private UpdateReviewCommand updateCommand;
    
    private final Long reservationId = 1L;
    private final Integer stars = 5;
    private final String comment = "Excellent service!";
    private final Long reviewerGuestId = 1L;
    private final Long reviewedHostId = 2L;
    
    @BeforeEach
    void setUp() {
        // Mock CreateReviewCommand
        createCommand = mock(CreateReviewCommand.class);
        when(createCommand.reservationId()).thenReturn(reservationId);
        when(createCommand.stars()).thenReturn(stars);
        when(createCommand.comment()).thenReturn(comment);
        when(createCommand.reviewerGuestId()).thenReturn(reviewerGuestId);
        when(createCommand.reviewerHostId()).thenReturn(null);
        when(createCommand.reviewedGuestId()).thenReturn(null);
        when(createCommand.reviewedHostId()).thenReturn(reviewedHostId);
        
        // Mock UpdateReviewCommand
        updateCommand = mock(UpdateReviewCommand.class);
        when(updateCommand.stars()).thenReturn(4);
        when(updateCommand.comment()).thenReturn("Updated comment");
    }
    
    @Test
    void testConstructorWithCreateCommand() {
        // Act
        Review review = new Review(createCommand);
        
        // Assert
        assertNotNull(review);
        assertEquals(stars, review.getRating().stars());
        assertEquals(comment, review.getComment());
        assertEquals(new GuestId(reviewerGuestId), review.getReviewerGuestId());
        assertNull(review.getReviewerHostId());
        assertNull(review.getReviewedGuestId());
        assertEquals(new HostId(reviewedHostId), review.getReviewedHostId());
    }
    
    @Test
    void testUpdateReview() {
        // Arrange
        Review review = new Review(createCommand);
        
        // Act
        Review updatedReview = review.updateReview(updateCommand);
        
        // Assert
        assertNotNull(updatedReview);
        assertSame(review, updatedReview, "Should return the same instance");
        assertEquals(4, updatedReview.getRating().stars());
        assertEquals("Updated comment", updatedReview.getComment());
    }
    
    @Test
    void testCanReviewByGuestUser() {
        // Arrange
        Review review = new Review(createCommand);
        
        // Act & Assert
        assertTrue(review.canReviewBy(reviewerGuestId, false));
        assertFalse(review.canReviewBy(999L, false));
    }
    
    @Test
    void testDefaultConstructor() {
        // Act
        Review review = new Review();
        
        // Assert
        assertNotNull(review);
        assertNull(review.getId());
        assertNull(review.getRating());
        assertNull(review.getComment());
        assertNull(review.getReviewerGuestId());
        assertNull(review.getReviewerHostId());
        assertNull(review.getReviewedGuestId());
        assertNull(review.getReviewedHostId());
    }
}

class RatingTest {
    
    @Test
    void testValidRating() {
        // Test valid ratings
        for (int i = 1; i <= 5; i++) {
            Rating rating = new Rating(i);
            assertEquals(i, rating.stars());
        }
    }
    
    @Test
    void testInvalidRatingBelowMin() {
        assertThrows(IllegalArgumentException.class, () -> new Rating(0));
        assertThrows(IllegalArgumentException.class, () -> new Rating(-1));
    }
    
    @Test
    void testInvalidRatingAboveMax() {
        assertThrows(IllegalArgumentException.class, () -> new Rating(6));
        assertThrows(IllegalArgumentException.class, () -> new Rating(10));
    }
    
    @Test
    void testNullRating() {
        assertThrows(IllegalArgumentException.class, () -> new Rating(null));
    }
    
    @Test
    void testDefaultConstructor() {
        Rating rating = new Rating();
        assertEquals(1, rating.stars());
    }
}
