package de.mle.stackoverflow.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class SpyTest {
    @Spy
    private MyTestClass myTestClass;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void spyInsteadOfPowermock() {
        when(myTestClass.getObject()).thenReturn(Integer.valueOf(3));

        assertThat(myTestClass.doSomethingMethod()).isEqualTo("3");
    }

    class MyTestClass{
        public String doSomethingMethod(){
            return getObject().toString();
        }

        Object getObject() {
            return new Object();
        }
    }
}
