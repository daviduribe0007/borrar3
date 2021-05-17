package com.example.card.web.flux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
public class CardService {

    private static CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public static Flux<Card> listAll() {
        return cardRepository.findAll();
    }

    public Mono<Void> insert(Mono<Card> cardMono){
        return cardMono
                .flatMap(cardRepository::save).then();
    }

    public Flux<Card> listByType(String type) {
        return cardRepository.findByType(type);
    }

    public Mono<Void> delete(String code) {
        return cardRepository.deleteById(code);
    }

    public Mono<Void> update(Mono<Card> cardMono){
        return cardMono
                .flatMap(cardRepository::save).then();
    }





}
