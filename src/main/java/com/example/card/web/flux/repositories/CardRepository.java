package com.example.card.web.flux.repositories;

import com.example.card.web.flux.entities.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CardRepository extends ReactiveMongoRepository<Card, String> {
    Flux<Card> findByType(String type);
}
