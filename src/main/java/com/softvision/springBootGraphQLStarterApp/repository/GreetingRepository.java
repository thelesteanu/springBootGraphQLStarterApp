package com.softvision.springBootGraphQLStarterApp.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
public class GreetingRepository {
    public String getBasic() {
        return "Hello world!";
    }

    public Mono<String> getGreeting() {
        return Mono.delay(Duration.ofMillis(50)).map(aLong -> "Hello!");
    }

    public Flux<String> getGreetings() {
        return Mono.delay(Duration.ofMillis(50))
                .flatMapMany(aLong -> Flux.just("Hi!", "Bonjour!", "Hola!", "Ciao!", "Salut!"));
    }

    public Flux<String> getGreetingsStream() {
        return Mono.delay(Duration.ofMillis(50))
                .flatMapMany(aLong -> Flux.just("Hi!", "Bonjour!", "Hola!", "Ciao!", "Salut!"));
    }
}
