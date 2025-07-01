package com.homeypark.web_service.reservations.domain.model.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public record Rating(Integer stars) {
    public Rating {
        if (stars == null || stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5 stars");
        }
    }

    public Rating() { 
        this(1); 
    }
}
