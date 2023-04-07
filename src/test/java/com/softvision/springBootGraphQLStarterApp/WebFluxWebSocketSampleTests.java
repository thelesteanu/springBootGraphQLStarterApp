package com.softvision.springBootGraphQLStarterApp;

import com.softvision.springBootGraphQLStarterApp.controller.GreetingController;
import com.softvision.springBootGraphQLStarterApp.repository.AuthorRepository;
import com.softvision.springBootGraphQLStarterApp.repository.BookRepository;
import com.softvision.springBootGraphQLStarterApp.repository.GreetingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

/**
 * GraphQL over WebSocket, server-side tests, i.e. without a client.
 */
@GraphQlTest(GreetingController.class)
@Import(GreetingRepository.class)
public class WebFluxWebSocketSampleTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    BookRepository bookRepository;


//    @Test
    void greetingMono() {
        this.graphQlTester.document("{greetingMono}")
                .execute()
                .path("greetingMono")
                .entity(String.class)
                .isEqualTo("Hello!");
    }

//    @Test
    void greetingsFlux() {
        this.graphQlTester.document("{greetingsFlux}")
                .execute()
                .path("greetingsFlux")
                .entityList(String.class)
                .containsExactly("Hi!", "Bonjour!", "Hola!", "Ciao!", "Salut!");
    }

    @Test
    void subscriptionWithEntityPath() {
        Flux<String> result = this.graphQlTester.document("subscription { greetings }")
                .executeSubscription()
                .toFlux("greetings", String.class);

        StepVerifier.create(result)
                .expectNext("Hi!")
                .expectNext("Bonjour!")
                .expectNext("Hola!")
                .expectNext("Ciao!")
                .expectNext("Salut!")
                .verifyComplete();
    }

    @Test
    void subscriptionWithResponse() {
        Flux<GraphQlTester.Response> result = this.graphQlTester.document("subscription { greetings }")
                .executeSubscription()
                .toFlux();

        StepVerifier.create(result)
                .consumeNextWith(response -> response.path("greetings").hasValue())
                .consumeNextWith(response -> response.path("greetings").matchesJson("\"Bonjour!\""))
                .consumeNextWith(response -> response.path("greetings").matchesJson("\"Hola!\""))
                .expectNextCount(2)
                .verifyComplete();
    }

}
