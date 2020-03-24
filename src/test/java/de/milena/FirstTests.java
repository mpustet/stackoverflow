package de.milena;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTests {

    @Test
    public void firstLetter() {
        String name = "Milena";
        assertThat(name).startsWith("M");
    }
}
