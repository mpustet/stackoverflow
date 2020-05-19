package de.mle.stackoverflow.reactive;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class ListsAndFluxes {
    @Test
    public void fluxBackedList() {
        List<Integer> a = List.of(1, 2, 3);
        List<Integer> b = List.of(4, 5, 6);


        Flux.just(a, b).parallel()
                .flatMap(list -> Flux.fromIterable(list))
                .subscribe(item -> log.warn("Item in flux {}", item));

        Stream.of(a, b).parallel()
                .flatMap(List::stream)
                .forEach(item -> log.warn("Item in stream {}", item));
    }

    @Test
    public void onNull() {
        Mono.justOrEmpty(null).log();
    }
}
