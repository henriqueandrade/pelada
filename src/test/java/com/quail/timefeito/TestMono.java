package com.quail.timefeito;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class TestMono {

    @Test
    public void testMono_001(){

        Mono<String> mono = Mono.empty();
    }

    public void testMono_002(){

        Mono<String> mono = Mono.just("Peladeiro");
        mono.subscribe(System.out::println);
    }

    @Test
    public void testMono_003(){

        Mono<Integer> mono = Mono.just(10);
        mono.subscribe(System.out::println);
    }

    @Test
    public void testMono_004(){

        Mono<String> mono = Mono.error(new RuntimeException("Ocorreu uma Exceção"));
    }
}
