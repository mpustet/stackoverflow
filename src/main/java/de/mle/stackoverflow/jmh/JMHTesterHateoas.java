package de.mle.stackoverflow.jmh;

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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import de.mle.stackoverflow.StackOverflowController;

@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(warmups = 1, value = 1)
public class JMHTesterHateoas {
	private static final int ITERATIONS = 1_000;

	public static void main(String[] args) throws RunnerException {
		new Runner(new OptionsBuilder().include(".*" + JMHTesterHateoas.class.getSimpleName() + ".*").build()).run();
	}

	@Benchmark
	public void linkPlain(Blackhole blackhole) throws IOException {
		for (int i = 0; i < ITERATIONS; i++)
			blackhole.consume(WebMvcLinkBuilder.linkTo(methodOn(StackOverflowController.class).link("a b")).withSelfRel().getHref());
	}

	@Benchmark
	public void linkWithExpand(Blackhole blackhole) throws IOException {
		Link link = linkTo(methodOn(StackOverflowController.class).link(null)).withSelfRel();
		for (int i = 0; i < ITERATIONS; i++)
			blackhole.consume(link.expand("a b").getHref());
	}
}