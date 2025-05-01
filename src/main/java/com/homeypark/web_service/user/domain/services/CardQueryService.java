package com.homeypark.web_service.user.domain.services;


import com.homeypark.web_service.user.domain.model.entities.Card;
import com.homeypark.web_service.user.domain.model.queries.GetAllCardsQuery;

import java.util.List;

public interface CardQueryService {
    List<Card> handle(GetAllCardsQuery query);
}
