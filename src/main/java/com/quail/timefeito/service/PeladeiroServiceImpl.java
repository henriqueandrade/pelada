package com.quail.timefeito.service;

import com.quail.timefeito.model.Peladeiro;
import com.quail.timefeito.model.SelecaoPeladeiros;
import com.quail.timefeito.repository.PeladeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PeladeiroServiceImpl implements PeladeiroService{

    @Autowired
    private PeladeiroRepository repository;

    public PeladeiroServiceImpl(PeladeiroRepository repository) {
        this.repository = repository;
    }


    @Override
    public Flux<Peladeiro> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<ResponseEntity<Peladeiro>> getOne(String id) {
        return repository.findById(id)
                .map(peladeiro -> ResponseEntity.ok(peladeiro))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<Peladeiro> save(Peladeiro peladeiro) {
        return repository.save(peladeiro);
    }

    @Override
    public Mono<ResponseEntity<Peladeiro>> update(String id, Peladeiro peladeiro) {
        return repository.findById(id)
                .flatMap(peladeiroExistente -> {
                    peladeiroExistente.setNome(peladeiro.getNome());
                    peladeiroExistente.setNivel(peladeiro.getNivel());
                    peladeiroExistente.setPosicao(peladeiro.getPosicao());
                    return repository.save(peladeiroExistente);
                })
                .map(updatePeladeiro -> ResponseEntity.ok(updatePeladeiro))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> delete(String id) {
        return repository.findById(id)
                .flatMap(peladeiroExistente ->
                        repository.delete(peladeiroExistente)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    @Override
    public Flux<SelecaoPeladeiros> getSelecoes(Integer peladeiroPorSelecao, List<String> peladeiroIds) {
        int media;
        Flux<Peladeiro> peladeiros = repository.findAll();
        List<Peladeiro> selecionados = peladeiros.toStream().filter(peladeiro -> peladeiroIds.contains(peladeiro.getId())).collect(Collectors.toList());
        media = selecionados.stream().flatMapToInt(peladeiro -> IntStream.of(peladeiro.getNivel())).sum();

        Integer timeId = 1;

        Random rand = new Random ();
        List<SelecaoPeladeiros> times = new ArrayList<SelecaoPeladeiros> ();
        SelecaoPeladeiros selecao;
        List<Peladeiro> jogadoresDoTime = new ArrayList<>();

        while(times.size() > peladeiroPorSelecao){
            selecao = new SelecaoPeladeiros ();
            jogadoresDoTime.add(selecionados.get(rand.nextInt(selecionados.size())));
        }

        return null;
    }
}
