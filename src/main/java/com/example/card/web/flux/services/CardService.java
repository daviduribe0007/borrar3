package com.example.card.web.flux.services;

import com.example.card.web.flux.entities.Card;
import com.example.card.web.flux.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.card.web.flux.services.AsignType.generateType;


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


    public Mono<Card> insert(Mono<Card> cardMono) {

        return cardMono
                .flatMap(card ->{
                    card.setType( generateType(card.getCode()));
                        return cardRepository.save(card); });


    }

    public Flux<Card> listByType(String type) {
        return cardRepository.findByType(type);
    }

    public Mono<Void> delete(String code) {
        return cardRepository.deleteById(code);
    }

    public Mono<Card> update(Mono<Card> cardMono) {
        return cardMono
                .flatMap(card ->{
                    card.setType( generateType(card.getCode()));
                    return cardRepository.save(card); });
    }


}
