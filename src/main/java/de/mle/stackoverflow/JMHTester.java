package de.mle.stackoverflow;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openjdk.jmh.runner.RunnerException;

//@State(Scope.Benchmark)
//@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
//@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
//@Fork(warmups = 1, value = 1)
public class JMHTester {
	public static void main(String[] args) throws IOException, RunnerException {
		org.openjdk.jmh.Main.main(args);
	}

	private static List<Integer> list = IntStream.range(0, 10_000).boxed().collect(Collectors.toList());

	// @Benchmark
	public void iterateWithStreams() {
		list.forEach(i -> {
			i++;
		});
	}

	// @Benchmark
	public void iterateWithFor() {
		for (int i = 0; i < 10_000; i++) {
			int j = list.get(i);
			j++;
		}
	}

	// @Benchmark
	public void iterateWithForEachWithInteger() {
		for (Integer i : list) {
			i++;
		}
	}

	// @Benchmark
	public void iterateWithForEachWithInt() {
		for (int i : list) {
			i++;
		}
	}
}
