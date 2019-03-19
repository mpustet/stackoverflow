package de.mle.stackoverflow.assertj;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AssertJFeatureTest {
    @Test
    public void showDoReturn() {
        assertThat(List.of(new Container(new Address("Caseros1", "Argentina"))))
                .anySatisfy(p -> {
                    assertThat(p.getAddress().getCity()).withFailMessage("expected city: " + "Caseros1").isEqualTo("Caseros1");
                    assertThat(p.getAddress().getCountryName()).isEqualTo("Argentina");
                });
    }

    @Data
    @AllArgsConstructor
    public class Container {
        private Address address;
    }

    @Data
    @AllArgsConstructor
    public class Address {
        private String city;
        private String countryName;
    }
}
