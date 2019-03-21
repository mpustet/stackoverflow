package de.mle.stackoverflow.reactive;

import java.time.Duration;
import java.util.function.Supplier;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoAndFlux {
    private Service service = new Service();

    @Test
    public void delayMono() {
        // when
        Supplier<Mono<Integer>> runnable = () -> service.getDelayedInteger();

        // then
        StepVerifier.withVirtualTime(runnable)// From here we talk about virtual time.
                .expectSubscription() // No time yet elapsed
                .expectNoEvent(Duration.ofSeconds(1)) // No event (item) for 1 second
                .expectNext(1) // first event (item) arrives
                .expectComplete() // Assert the completion signal.
                .verify(Duration.ofMillis(100)); // Everything takes max. 100 ms in real-time!
    }

    @Test
    public void delayFlux() {
        // when
        Supplier<Flux<Integer>> runnable = () -> service.getDelayedIntegers();

        // then
        StepVerifier.withVirtualTime(runnable)// From here we talk about virtual time.
                .expectSubscription() // No time yet elapsed
                .expectNoEvent(Duration.ofSeconds(1)) // No event (item) for 1 second
                .expectNext(1) // first event (item) arrives
                .expectNoEvent(Duration.ofSeconds(1)) // no further events for another second
                .expectNext(2) // second event (item) arrives
                .thenAwait(Duration.ofSeconds(2)) // let the time pass…
                .expectNext(3, 4) // …and expect the last both items
                .expectComplete() // Assert the completion signal.
                .verify(Duration.ofMillis(100)); // Everything takes max. 100 ms in real-time!
    }

    class Service {
        Mono<Integer> getDelayedInteger() {
            return Mono.just(1).delayElement(Duration.ofSeconds(1));
        }

        Flux<Integer> getDelayedIntegers() {
            return Flux.just(1, 2, 3, 4).delayElements(Duration.ofSeconds(1));
        }
    }
}
