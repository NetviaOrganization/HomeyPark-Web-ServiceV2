package com.homeypark.web_service.user.interfaces.rest.resources;


public record CardResource(
        Double numCard,
        Double cvv,
        String date,
        String holder,
        Long profileId
) {
}
