package com.example.card.web.flux;

import com.example.card.web.flux.controller.CardController;
import com.example.card.web.flux.entities.Card;
import com.example.card.web.flux.repositories.CardRepository;
import com.example.card.web.flux.services.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CardController.class)
class CardControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private CardService cardService;

    @MockBean
    private CardRepository repository;

    @Captor
    private ArgumentCaptor<Mono<Card>> argumentCaptor;

    @Test
    void list() {
        var list = Flux.just(
                new Card("06", "Nueva", LocalDate.of(2020, 05, 2), "154565999"));
        when(repository.findAll()).thenReturn(list);
        webTestClient.get()
                .uri("/card")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].code").isEqualTo("06")
                .jsonPath("$[0].title").isEqualTo("Nueva")
                .jsonPath("$[0].date").isEqualTo("2020-05-02")
                .jsonPath("$[0].number").isEqualTo("154565999");


    }

    @Test
    void delete() {
        webTestClient.delete()
                .uri("/card/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }
    @Test
    void update() {
        var request = Mono.just(new Card("06","Nueva",  LocalDate.of(2020,05,2),"154565999"));
        webTestClient.put()
                .uri("/card")
                .body(request, Card.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

    @Test
    void get() {
        webTestClient.get()
                .uri("/card/VISA")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Card.class)
                .consumeWith(cardEntityExchangeResult -> {
                    var card = cardEntityExchangeResult.getResponseBody();
                    assert card != null;
                });
    }
}