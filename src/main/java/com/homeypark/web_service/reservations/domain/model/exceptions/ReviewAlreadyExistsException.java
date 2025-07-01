package com.homeypark.web_service.reservations.domain.model.exceptions;

public class ReviewAlreadyExistsException extends RuntimeException {
    public ReviewAlreadyExistsException() {
        super("Review already exists for this reservation");
    }
    
    public ReviewAlreadyExistsException(String message) {
        super(message);
    }
}
