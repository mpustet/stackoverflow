/*
package de.mle.stackoverflow.jackson;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.verify;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
class JsonControllerTest{
    @Autowired
    private JsonController jsonController;

    @Test
    void getFlux() {


       // given
        Flux
                .range(1, 100)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(System.out::println);

        // when
        Flux<Integer> flux = jsonController.getFlux();
        //eher so: endpunkt aufrufen, testen, ob zb (verify) 100 mal systemprtln aufgerufen wurde ?

        //then
*/
/*        StepVerifier.create()
                .expectNext(1)
                .expectNextMatches(number -> number.toString().length()<2)  //macht keinen sinn, wenn dann schauen, dass max 3 stellen, also nichts größer als 4
                .expectComplete()
                .verify();*//*



    }
}
*/
