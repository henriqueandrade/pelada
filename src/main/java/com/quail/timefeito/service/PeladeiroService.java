package com.quail.timefeito.service;

import com.quail.timefeito.model.Peladeiro;
import com.quail.timefeito.model.SelecaoPeladeiros;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PeladeiroService {
    Flux<Peladeiro> getAll();
    Mono<ResponseEntity<Peladeiro>> getOne(String id);
    Mono<Peladeiro> save(Peladeiro peladeiro);
    Mono<ResponseEntity<Peladeiro>> update(String id, Peladeiro peladeiro);
    Mono<ResponseEntity<Void>> delete(String id);
    Mono<Void> deleteAll();
    Flux<SelecaoPeladeiros> getSelecao(Integer quantidadeSelecoes,List<String> peladeiroIds);
}
