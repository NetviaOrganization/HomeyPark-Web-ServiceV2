package com.homeypark.web_service.user.interfaces.rest;

import com.homeypark.web_service.user.domain.model.commands.DeleteCardCommand;
import com.homeypark.web_service.user.domain.model.queries.GetAllCardsQuery;
import com.homeypark.web_service.user.domain.services.CardCommandService;
import com.homeypark.web_service.user.domain.services.CardQueryService;
import com.homeypark.web_service.user.interfaces.rest.resources.CardResource;
import com.homeypark.web_service.user.interfaces.rest.resources.CreateCardResource;
import com.homeypark.web_service.user.interfaces.rest.transformers.CardResourceFromEntityAssembler;
import com.homeypark.web_service.user.interfaces.rest.transformers.CreateCardCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cards")
public class CardController {
    private final CardQueryService cardQueryService;
    private final CardCommandService cardCommandService;

    public CardController(CardQueryService cardQueryService, CardCommandService cardCommandService) {
        this.cardQueryService = cardQueryService;
        this.cardCommandService = cardCommandService;
    }

    @PostMapping("/create")
    public ResponseEntity<CardResource> createCard(@RequestBody CreateCardResource createCardResource) {
        var createCardCommand = CreateCardCommandFromResourceAssembler.toCommandFromResource(createCardResource);

        var card = cardCommandService.handle(createCardCommand).map(CardResourceFromEntityAssembler::toResourceFromEntity);

        return card.map(c -> new ResponseEntity<>(c, HttpStatus.CREATED)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    public ResponseEntity<List<CardResource>> getAllCards() {

        var getAllCardsQuery = new GetAllCardsQuery();
        var cards = cardQueryService.handle(getAllCardsQuery)
                .stream()
                .map(CardResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id){
        var deleteCardCommand = new DeleteCardCommand(id);
        cardCommandService.handle(deleteCardCommand);
        return ResponseEntity.noContent().build();
    }

}
