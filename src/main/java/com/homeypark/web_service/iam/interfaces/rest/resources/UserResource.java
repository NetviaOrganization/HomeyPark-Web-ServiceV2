package com.homeypark.web_service.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String email, List<String> roles) {
}
