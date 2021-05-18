package com.example.card.web.flux;

import com.example.card.web.flux.controller.CardController;
import com.example.card.web.flux.entities.Card;
import com.example.card.web.flux.repositories.CardRepository;
import com.example.card.web.flux.services.CardService;
import com.example.card.web.flux.valueobjects.TypeCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.http.MediaType;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
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
    void getAll() {
        var list = Flux.just(
                new Card("06", "Nueva", LocalDate.of(2020, 05, 2), "154565999"));
        when(repository.findAll()).thenReturn(list);
        webTestClient.get()
                .uri("/card")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].code").isEqualTo(list.blockFirst().getCode())
                .jsonPath("$[0].title").isEqualTo(list.blockFirst().getTitle())
                .jsonPath("$[0].date").isEqualTo(list.blockFirst().getDate().toString())
                .jsonPath("$[0].number").isEqualTo(list.blockFirst().getNumber());
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
    void saveCard() {

        var request = Mono.just(new Card(
                "06",
                "Nueva",
                LocalDate.of(2020, 05, 2),
                "154565999"));
        when(repository.save(any(Card.class))).thenReturn(request);
        webTestClient.post()
                .uri("/card")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(request, Card.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.code").isEqualTo(request.block().getCode())
                .jsonPath("$.title").isEqualTo(request.block().getTitle())
                .jsonPath("$.date").isEqualTo(request.block().getDate().toString())
                .jsonPath("$.number").isEqualTo(request.block().getNumber());
    }


    @Test
    void update() {
        var request = Mono.just(new Card(
                "06",
                "Nueva",
                LocalDate.of(2020, 05, 2),
                "154565999"));
        when(repository.save(any(Card.class))).thenReturn(request);
        webTestClient.put()
                .uri("/card")
                .body(request, Card.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.code").isEqualTo(request.block().getCode())
                .jsonPath("$.title").isEqualTo(request.block().getTitle())
                .jsonPath("$.date").isEqualTo(request.block().getDate().toString())
                .jsonPath("$.number").isEqualTo(request.block().getNumber());
    }

    @Test
    void getByType2() {
        var list = Flux.just(
                new Card("06", "Nueva", LocalDate.of(2020, 05, 2), "154565999"));
        when(repository.findByType("VISA")).thenReturn(list);
        webTestClient.get()
                .uri("/card/VISA")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].code").isEqualTo(list.blockFirst().getCode())
                .jsonPath("$[0].title").isEqualTo(list.blockFirst().getTitle())
                .jsonPath("$[0].date").isEqualTo(list.blockFirst().getDate().toString())
                .jsonPath("$[0].number").isEqualTo(list.blockFirst().getNumber());
        verify(cardService).listByType("VISA");
        verify(repository).findByType("VISA");
    }

    @Test
    void getById() {
        var request = Mono.just(
                new Card("06", "Nueva", LocalDate.of(2020, 05, 2), "154565999"));
        when(repository.findById("154565999")).thenReturn(request);
        webTestClient.get()
                .uri("/card/id/154565999")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.code").isEqualTo(request.block().getCode())
                .jsonPath("$.title").isEqualTo(request.block().getTitle())
                .jsonPath("$.date").isEqualTo(request.block().getDate().toString())
                .jsonPath("$.number").isEqualTo(request.block().getNumber());

    }
}