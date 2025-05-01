package com.homeypark.web_service.user.domain.services;



import com.homeypark.web_service.user.domain.model.commands.CreateCardCommand;
import com.homeypark.web_service.user.domain.model.commands.DeleteCardCommand;
import com.homeypark.web_service.user.domain.model.entities.Card;

import java.util.Optional;

public interface CardCommandService {
    Optional<Card> handle(CreateCardCommand command);
    void handle(DeleteCardCommand command);
}
