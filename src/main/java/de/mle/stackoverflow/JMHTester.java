package de.mle.stackoverflow;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.RunnerException;

@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(warmups = 1, value = 1)
public class JMHTester {
	public static void main(String[] args) throws IOException, RunnerException {
		org.openjdk.jmh.Main.main(args);
	}

	@Benchmark
	public void iterateWithStreams() {
		IntStream.of(10_000).forEach(i -> i++);
	}

	@Benchmark
	public void iterateWithFor() {
		for (int i = 0; i < 10_000; i++)
			i++;
	}
}
