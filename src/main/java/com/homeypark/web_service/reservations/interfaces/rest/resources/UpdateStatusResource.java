package com.homeypark.web_service.reservations.interfaces.rest.resources;

import com.homeypark.web_service.reservations.domain.model.valueobject.Status;

public record UpdateStatusResource(Status status) {
}
