package com.example.card.web.flux;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CardRepository extends ReactiveMongoRepository<Card, String> {
    Mono<Card> findBycode(String code);
}
