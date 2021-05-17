package com.example.card.web.flux;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
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
                new Card("06","Nueva",  LocalDate.of(2020,05,2),"154565999"));
        when(repository.findAll()).thenReturn(list);
        webTestClient.get()
                .uri("/card")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].code").isEqualTo("06")
                .jsonPath("$[0].title").isEqualTo("Nueva")
                .jsonPath("$[0].date").isEqualTo("2020-05-02")
                .jsonPath("$[0].number").isEqualTo("154565999")
                .jsonPath("$[0].type").isEqualTo("VISA");
    }
}