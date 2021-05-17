package com.example.card.web.flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public Mono<Void> save(@RequestBody Mono<Card> cardMonoMono) {
        return cardService.insert(cardMonoMono);
    }

    @GetMapping
    public Flux<Card> list() {
        return CardService.listAll();
    }




}
