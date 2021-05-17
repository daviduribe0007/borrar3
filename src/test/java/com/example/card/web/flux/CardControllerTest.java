package com.example.card.web.flux;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CardController.class)
class CardControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private CardService cardService;

    @MockBean
    private CardRepository repository;

    @Test
    void list() {
        var list = Flux.just(
                new Card("111","Nueva",  LocalDate.of(2020,05,2),"04",TypeCard.MasterCard));
        when(repository.findAll()).thenReturn(list);

        webTestClient.get()
                .uri("/card")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].code").isEqualTo("111")
                .jsonPath("$[0].title").isEqualTo("Nueva")
                .jsonPath("$[0].date").isEqualTo("2020-05-02")
                .jsonPath("$[0].number").isEqualTo("04")
                .jsonPath("$[0].type").isEqualTo("MasterCard");

    }
}