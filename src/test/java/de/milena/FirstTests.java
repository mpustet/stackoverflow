package de.milena;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FirstTests {

    @Test
    public void firstLetter() {
        String name = "Milena";
        assertThat(name).startsWith("M");
    }
}
