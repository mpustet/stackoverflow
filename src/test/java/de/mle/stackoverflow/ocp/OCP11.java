package de.mle.stackoverflow.ocp;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class OCP11 {
    public static void main(String[] args) {
        System.out.println("Args: " + args[0]);
    }

    @Test
    void intStream() {
        Stream<Integer> s = Stream.of(1, 2, 3);
        s.map(n -> n + 1)
                .peek(n -> System.out.print(n));
        s.forEach(n -> System.out.println(n));
    }

    @Test
    void varArgs() {
        System.out.println("Length " + howMany(true, new boolean[2]));
    }

    private int howMany(boolean b, boolean... b2) {
        return b2.length;
    }
}
