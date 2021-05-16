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
    public Mono<Void> post(@RequestBody Mono<Card> personMono) {
        return cardService.insert(personMono);
    }

    @GetMapping("/{code}")
    public Mono<Card> getPerson(@PathVariable("code") String id) {
        return Mono.just(new Card());
    }

    @PutMapping
    public Mono<Void> update(@RequestBody Mono<Card> personMono) {
        return Mono.empty();
    }

    @DeleteMapping("/{code}")
    public Mono<Void> delete(@PathVariable("code") String id) {
        return Mono.empty();
    }

    @GetMapping
    public Flux<Card> list() {
        return cardService.listAll();
    }
}
