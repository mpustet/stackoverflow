package de.mle.stackoverflow.mockito;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MockitoFeatureTest {
    @Test
    public void showMocking() {
        Greeter greeter = mock(Greeter.class);
        when(greeter.greet()).thenReturn("Bye!");
        assertThat(greeter.greet()).isEqualTo("Bye!");
        
    }

    @Test
    public void showDoReturn() {
        Greeter greeter = mock(Greeter.class);
        doReturn("Bye!").when(greeter).greet();
        assertThat(greeter.greet()).isEqualTo("Bye!");
    }

    @Test
    public void showMatches() {
        // given
        Greeter greeter = mock(Greeter.class);
        when(greeter.greet(matches(".*\\d+\\w.*"))).thenReturn("Matcher matched");

        // when && then
        assertThat(greeter.greet("none matching")).isNull();
        assertThat(greeter.greet("123matching")).isEqualTo("Matcher matched");
    }

    class Greeter {
        String greet() {
            return "Hæ!";
        }
        String greet(String name) {
            return "Hæ" + name + "!";
        }
    }
}
