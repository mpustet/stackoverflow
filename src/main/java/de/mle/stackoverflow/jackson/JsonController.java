package de.mle.stackoverflow.jackson;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;

@RestController
public class JsonController {
    @GetMapping(path = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getJson() {
        return "{ \"singleItemList\" : 3 }";
    }

    @GetMapping(path = "/flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getFlux() {
        return Flux
                .fromStream(Stream.generate(() -> new Random().nextInt()))
                .delayElements(Duration.ofMillis(500));
    }
}
