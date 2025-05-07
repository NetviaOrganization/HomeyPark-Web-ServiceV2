package com.homeypark.web_service.iam.domain.model.commands;

import com.homeypark.web_service.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String email, String username, String password, List<Role> roles) {
}
