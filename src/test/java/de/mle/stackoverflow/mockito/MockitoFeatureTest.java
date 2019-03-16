package de.mle.stackoverflow.mockito;

import org.junit.Test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class MockitoFeatureTest {
    @Test
    public void showDoReturn() {
        Greeter greeter = mock(Greeter.class);
        doReturn("Bye!").when(greeter).greet();
    }

    class Greeter {
        String greet() {
            return "Hæ!";
        }
    }
}
