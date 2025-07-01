package com.homeypark.web_service.reservations.domain.model.exceptions;

public class InvalidReviewStateException extends RuntimeException {
    public InvalidReviewStateException() {
        super("Cannot create review for reservation that is not completed");
    }
    
    public InvalidReviewStateException(String message) {
        super(message);
    }
}
