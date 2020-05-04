package de.mle.stackoverflow.reactive;

import java.time.Duration;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

@Slf4j
public class ZipperTest {
    @Test
    void monoZip() {
        // given
        Mono<Integer> one = Mono.just(1);
        Mono<Integer> two = Mono.just(2);

        // when
        Mono<Integer> sum = Mono.zip(one, two).map(t -> t.getT1() + t.getT2());

        // then
        StepVerifier.create(sum).expectNext(3).verifyComplete();
    }

    @Test
    void fluxZip() {
        // given
        Flux<Integer> oneToFive = Flux.range(1, 5);
        Flux<Integer> elevenToFifteen = Flux.range(11, 5);

        // when
        Flux<Integer> sums = Flux
                .zip(oneToFive, elevenToFifteen)
                .map(t -> t.getT1() + t.getT2());

        // then
        StepVerifier.create(sums).expectNext(12, 14, 16, 18, 20).verifyComplete();
    }

    @Test
    void monoZipWithRealTime() {
        // given
        Mono<Integer> intWebService = Mono.just(3).delayElement(Duration.ofSeconds(5));
        Mono<String> stringWebService = Mono.just("3 is c");
        intWebService.subscribe(i -> log.info("Received int {}", i));
        stringWebService.subscribe(s -> log.info("Received string '{}'", s));

        // when
        Mono<Pair<Integer, String>> zipped = Mono.zip(intWebService, stringWebService).map(t -> Pair.of(t.getT1(), t.getT2()));

        // then
        StepVerifier.create(zipped)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(4))
                .expectNext(Pair.of(3, "3 is c"))
                .expectComplete()
                .verify();
    }

    @Test
    void monoZipWithVirtualTime() {
        // given
        VirtualTimeScheduler.getOrSet();
        Mono<Integer> intWebService = Mono.just(3).delayElement(Duration.ofSeconds(5));
        Mono<String> stringWebService = Mono.just("3 is c");
        intWebService.subscribe(i -> log.info("Received int {}", i));
        stringWebService.subscribe(s -> log.info("Received string '{}'", s));

        // when
        Mono<Pair<Integer, String>> zipped = Mono.zip(intWebService, stringWebService).map(t -> Pair.of(t.getT1(), t.getT2()));

        // then
        StepVerifier.withVirtualTime(() -> zipped)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(4))
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(Pair.of(3, "3 is c"))
                .expectComplete()
                .verify(Duration.ofMillis(100));
    }
}
