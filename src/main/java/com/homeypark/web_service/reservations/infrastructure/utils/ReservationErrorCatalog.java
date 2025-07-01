package com.homeypark.web_service.reservations.infrastructure.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationErrorCatalog {
    PROFILE_NOT_FOUND("ERR_RESERVATION_001", "Profile not found (Guest or Host)"),
    VEHICLE_NOT_FOUND("ERR_RESERVATION_002", "Vehicle not found"),
    PARKING_NOT_FOUND("ERR_RESERVATION_003", "Parking not found"),
    RESERVATION_NOT_FOUND("ERR_RESERVATION_004", "Reservation not found"),
    SCHEDULE_CONFLICT("ERR_RESERVATION_005", "The schedule does not include the requested time range"),
    RESERVATION_OVERLAP("ERR_RESERVATION_006", "The requested schedule overlaps with an already approved reservation"),
    EMPTY_FILE("ERR_RESERVATION_007", "Empty file"),
    IMAGE_UPLOAD_ERROR("ERR_RESERVATION_008", "Image upload error"),
    RESERVATION_UPDATE_ERROR("ERR_RESERVATION_009", "Reservation update error"),
    
    // Review specific errors
    REVIEW_NOT_FOUND("ERR_REVIEW_001", "Review not found"),
    REVIEW_ALREADY_EXISTS("ERR_REVIEW_002", "Review already exists for this reservation"),
    UNAUTHORIZED_REVIEW("ERR_REVIEW_003", "User not authorized to create/modify this review"),
    INVALID_REVIEW_STATE("ERR_REVIEW_004", "Cannot create review for reservation that is not completed"),


    // invalid parameters
    INVALID_PARAMETER("ERR_INVALID_001", "Invalid parameter"),

    // invalid JSON
    INVALID_JSON("ERR_JSON_001", "Invalid JSON"),

    // generic errors
    GENERIC_ERROR("ERR_GENERIC_001", "Generic error");

    private final String code;
    private final String message;

}
