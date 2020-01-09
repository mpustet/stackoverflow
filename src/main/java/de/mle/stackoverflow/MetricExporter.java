package de.mle.stackoverflow;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MetricExporter {
    private final MeterRegistry registry;

    @PostConstruct
    public void count() {
        Gauge
                .builder("sample-count", () -> new Random().nextInt(10))
                .description("indicates a random offer count")
                .tags("app", "stackoverflow", "stage", "prod", "host", "http://localhost")
                .register(registry);
    }
}
