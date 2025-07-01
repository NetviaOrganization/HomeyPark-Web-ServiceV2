package com.homeypark.web_service.reservations.domain.model.exceptions;

public class UnauthorizedReviewException extends RuntimeException {
    public UnauthorizedReviewException() {
        super("User not authorized to create/modify this review");
    }
    
    public UnauthorizedReviewException(String message) {
        super(message);
    }
}
