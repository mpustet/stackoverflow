package de.mle.stackoverflow;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;
import org.springframework.hateoas.Link;

@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(warmups = 1, value = 1)
public class JMHTesterHateoas {
	private static final int ITERATIONS = 2_500;

	public static void main(String[] args) throws IOException, RunnerException {
		org.openjdk.jmh.Main.main(args);
	}

	@Benchmark
	public void linkPlain(Blackhole blackhole) throws IOException {
		for (int i = 0; i < ITERATIONS; i++)
			blackhole.consume(linkTo(methodOn(StackOverflowController.class).link("a b")).withSelfRel().getHref());
	}

	@Benchmark
	public void linkWithExpand(Blackhole blackhole) throws IOException {
		Link link = linkTo(methodOn(StackOverflowController.class).link(null)).withSelfRel();
		for (int i = 0; i < ITERATIONS; i++)
			blackhole.consume(link.expand("a b").getHref());
	}
}