package de.mle.stackoverflow.jmh;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class JMHBranchPrediction {
    private static final int COUNT = 1024 * 1024;
    private byte[] sorted;
    private byte[] unsorted;

    @Setup
    public void setup() {
        sorted = new byte[COUNT];
        unsorted = new byte[COUNT];
        Random random = new Random(1234);
        random.nextBytes(sorted);
        random.nextBytes(unsorted);
        Arrays.sort(sorted);
    }

    @Benchmark
    @OperationsPerInvocation(COUNT)
    public void sorted(Blackhole bh1, Blackhole bh2) {
        for (byte v : sorted) {
            if (v > 0) {
                bh1.consume(v);
            } else {
                bh2.consume(v);
            }
        }
    }

    @Benchmark
    @OperationsPerInvocation(COUNT)
    public void unsorted(Blackhole bh1, Blackhole bh2) {
        for (byte v : unsorted) {
            if (v > 0) {
                bh1.consume(v);
            } else {
                bh2.consume(v);
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(".*" + JMHBranchPrediction.class.getSimpleName() + ".*").build()).run();
    }
}