package com.softvision.springBootGraphQLStarterApp.controller;

import com.softvision.springBootGraphQLStarterApp.repository.GreetingRepository;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class GreetingController {
    private final GreetingRepository greetingRepository;

    public GreetingController(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @QueryMapping
    public String greeting() {
        return this.greetingRepository.getBasic();
    }

    @QueryMapping
    public Mono<String> greetingMono() {
        return this.greetingRepository.getGreeting();
    }

    @QueryMapping
    public Flux<String> greetingsFlux() {
        return this.greetingRepository.getGreetings();
    }

    @SubscriptionMapping
    public Publisher<String> greetings() {
        return this.greetingRepository.getGreetingsStream();
    }

}
