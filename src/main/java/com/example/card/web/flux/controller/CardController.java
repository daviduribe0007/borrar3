package com.example.card.web.flux.controller;

import com.example.card.web.flux.entities.Card;
import com.example.card.web.flux.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Card> save(@RequestBody Mono<Card> cardMonoMono) {
        return cardService.insert(cardMonoMono);
    }

    @GetMapping
    public Flux<Card> list() {
        return CardService.listAll();
    }

    @GetMapping("/{type}")
    public Flux<Card> getCard(@PathVariable("type") String type) {
        return cardService.listByType(type);
    }

    @GetMapping("/id/{type}")
    public Mono<Card> getidCard(@PathVariable("type") String type) {
        return cardService.listById(type);
    }

    @DeleteMapping(value = "/{code}")
    public Mono<Void> delete(@PathVariable("code") String code) {
        return cardService.delete(code);
    }

    @PutMapping
    public Mono<Card> update(@RequestBody Mono<Card> cardMono) {
        return cardService.update(cardMono);
    }


}
