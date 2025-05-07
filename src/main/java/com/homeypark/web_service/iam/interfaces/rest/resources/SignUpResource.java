package com.homeypark.web_service.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String email, String username, String password, List<String> roles) {
}

