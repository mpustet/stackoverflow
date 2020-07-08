package de.mle.stackoverflow.jackson;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class JsonController {
    @GetMapping(path = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getJson() {
        return "{ \"singleItemList\" : 3 }";
    }

    @GetMapping(path = "/flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getFlux() {

        Flux
                .range(1, 20)
                .delayElements(Duration.ofSeconds(1))
                //.parallel(5) //parallelismus hier keine Auswirkungen
                .subscribe(System.out::println);

        Flux
                .range(1, 100)
                .filter(i -> i%2 != 0)
                .count()
                .subscribe(i -> System.out.println("Anzahl ungerader Elemente: " + i));

        return Flux
                .fromStream(Stream.generate(() -> new Random().nextInt()))
                .delayElements(Duration.ofMillis(500));
    }

    @GetMapping(path = "/moreFlux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<List<Long>> fluxPractice() {

        //create Flux from list, handling of errors and completionTasks
        List<String> list = List.of("Drei", "Vier", "Fuenf");
        Flux.fromIterable(list)
                .map(i ->
                {
                    if (i.length() <= 4) return i;
                    else throw new RuntimeException("Number of characters too high");
                })
                .doOnComplete(() -> System.out.println("Wird nur ausgeführt, wenn kein Error auftritt"))  //egal an welcher Stelle? Hauptsache innerhalb eines subscribers? Bezieht sich auf gesamte Logik?
                .doOnNext(i -> Mono.empty().defaultIfEmpty("Default value of empty Mono")
                        .subscribe(System.out::println))
                .subscribe(System.out::println, error -> System.err.println("Error: " + error),
                        () -> System.out.println("Entspricht doOnComplete? => kein Error aufgetreten"));

        return Flux
                .interval(Duration.ofMillis(200))
                .take(20)
                .collect(Collectors.toList())
                .doOnTerminate(() -> System.out.println("Trotz Error würde onTerminate ausgeführt werden"));
    }

}
