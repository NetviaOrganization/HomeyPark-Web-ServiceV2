package com.homeypark.web_service.user.application.internal.queryservices;

import com.homeypark.web_service.user.domain.model.entities.Card;
import com.homeypark.web_service.user.domain.model.queries.GetAllCardsQuery;
import com.homeypark.web_service.user.domain.services.CardQueryService;
import com.homeypark.web_service.user.infrastructure.repositories.jpa.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardQueryServiceImpl implements CardQueryService {

    private final CardRepository cardRepository;

    public CardQueryServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> handle(GetAllCardsQuery query) {
        return cardRepository.findAll();
    }

}
