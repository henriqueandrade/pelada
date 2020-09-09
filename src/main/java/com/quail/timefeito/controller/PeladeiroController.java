package com.quail.timefeito.controller;

import com.mongodb.MongoNodeIsRecoveringException;
import com.quail.timefeito.model.Peladeiro;
import com.quail.timefeito.model.PeladeiroEvent;
import com.quail.timefeito.repository.PeladeiroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/peladeiro")
public class PeladeiroController {

    private PeladeiroRepository repository;
    public PeladeiroController(PeladeiroRepository repository){this.repository = repository;}

    @GetMapping
    public Flux<Peladeiro> getAll() {return repository.findAll();}

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Peladeiro>> getOne(@PathVariable String id) {
        return repository.findById(id)
                .map(peladeiro -> ResponseEntity.ok(peladeiro))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Peladeiro> save(@RequestBody Peladeiro peladeiro) {
        return repository.save(peladeiro);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Peladeiro>> update(@PathVariable(value="id") String id,
                                                  @RequestBody Peladeiro peladeiro) {
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

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(value = "id") String id) {
        return repository.findById(id)
                .flatMap(peladeiroExistente ->
                        repository.delete(peladeiroExistente)
                        .then(Mono.just(ResponseEntity.ok().<Void>build()))
                        )
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @DeleteMapping
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    @GetMapping(value="/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PeladeiroEvent> getEvents(){
        return Flux.interval(Duration.ofSeconds(5))
                .map(val ->
                        new PeladeiroEvent(val, "Evento do Peladeiro"));
    }
}
