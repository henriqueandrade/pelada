package com.quail.timefeito;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class TestFlux {

    @Test
    void testFlux_001() {
        Flux.empty();
    }

    @Test
    void testFlux_002(){
        Flux<String> flux = Flux.just("Peladeiro");
        flux.subscribe(System.out::println);
    }

    @Test
    void testFlux_003(){
        Flux<String> flux = Flux.just("Carlos", "Andre", "Lucas");
        flux.subscribe(System.out::println);
    }
}
