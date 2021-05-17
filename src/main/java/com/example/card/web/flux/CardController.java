package com.example.card.web.flux;

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
    public Mono<Void> save(@RequestBody Mono<Card> cardMonoMono) {
        return cardService.insert(cardMonoMono);
    }

    @GetMapping
    public Flux<Card> list() {
        return CardService.listAll();
    }

    @GetMapping("/{type}")
    public Flux<Card> getPerson(@PathVariable("type") String type) {
        return cardService.listByType(type);
    }

    @DeleteMapping(value = "/{code}")
    public Mono<Void> delete(@PathVariable("code") String code) {
       return cardService.delete(code);
    }




}
