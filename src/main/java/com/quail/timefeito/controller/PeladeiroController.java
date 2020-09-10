package com.quail.timefeito.controller;

import com.quail.timefeito.model.Peladeiro;
import com.quail.timefeito.model.PeladeiroEvent;
import com.quail.timefeito.repository.PeladeiroRepository;
import com.quail.timefeito.service.PeladeiroServiceImpl;
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

    private PeladeiroServiceImpl service;
    //private PeladeiroRepository repository;
    //public PeladeiroController(PeladeiroRepository repository){this.repository = repository;}

    public PeladeiroController(PeladeiroServiceImpl service){this.service = service;}

    @GetMapping
    public Flux<Peladeiro> getAll() {return service.getAll();}

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Peladeiro>> getOne(@PathVariable String id) {
        return service.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Peladeiro> save(@RequestBody Peladeiro peladeiro) {
        return service.save(peladeiro);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Peladeiro>> update(@PathVariable(value="id") String id,
                                                  @RequestBody Peladeiro peladeiro) {
        return service.update(id, peladeiro);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(value = "id") String id) {
        return service.delete(id);

    }

    @DeleteMapping
    public Mono<Void> deleteAll() {
        return service.deleteAll();
    }

    @GetMapping(value="/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PeladeiroEvent> getEvents(){
        return Flux.interval(Duration.ofSeconds(5))
                .map(val ->
                        new PeladeiroEvent(val, "Evento do Peladeiro"));
    }
}
