package com.homeypark.web_service.user.interfaces.rest.transformers;


import com.homeypark.web_service.user.domain.model.entities.Card;
import com.homeypark.web_service.user.interfaces.rest.resources.CardResource;

public class CardResourceFromEntityAssembler {
    public static CardResource toResourceFromEntity(Card entity) {
        return new CardResource(
                entity.getNumCard(),
                entity.getCvv(),
                entity.getDate(),
                entity.getHolder(),
                entity.getProfile().getId()
        );
    }
}
