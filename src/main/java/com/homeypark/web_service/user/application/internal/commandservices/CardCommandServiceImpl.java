package com.homeypark.web_service.user.application.internal.commandservices;

import com.homeypark.web_service.user.domain.model.commands.CreateCardCommand;
import com.homeypark.web_service.user.domain.model.commands.DeleteCardCommand;
import com.homeypark.web_service.user.domain.model.entities.Card;
import com.homeypark.web_service.user.domain.services.CardCommandService;
import com.homeypark.web_service.user.infrastructure.repositories.jpa.CardRepository;
import com.homeypark.web_service.user.infrastructure.repositories.jpa.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardCommandServiceImpl implements CardCommandService {

    private final CardRepository cardRepository;
    private final ProfileRepository profileRepository;

    public CardCommandServiceImpl(CardRepository cardRepository, ProfileRepository profileRepository) {
        this.cardRepository = cardRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Card> handle(CreateCardCommand command) {
        Card card = new Card(command);
        try {
            var user = profileRepository.findById(command.userId());

            user.map((p)-> {
                card.setProfile(p);
                return p;
            }).orElseThrow(()-> new IllegalArgumentException("User not founded"));

            var response = cardRepository.save(card);
            return Optional.of(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void handle(DeleteCardCommand command){
        if (!cardRepository.existsById(command.cardId())) throw new IllegalArgumentException("Card not founded");
        cardRepository.deleteById(command.cardId());
        System.out.println("Card Delete");
    }

}
