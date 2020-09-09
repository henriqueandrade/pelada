package com.quail.timefeito;

import com.quail.timefeito.model.Peladeiro;
import com.quail.timefeito.repository.PeladeiroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class TimefeitoApplication {

	//O peladeiro
	public static void main(String[] args) {
		SpringApplication.run(TimefeitoApplication.class, args);
	}


	@Bean
	CommandLineRunner init (ReactiveMongoOperations operations,
							PeladeiroRepository repository) {
		return args -> {
			Flux<Peladeiro> peladeiroFlux = Flux.just(
					new Peladeiro(null, "Carlos", 8, "A"),
					new Peladeiro(null, "Paulo", 3, "A"),
					new Peladeiro(null, "Andre", 6, "M"),
					new Peladeiro(null, "Lucas", 4, "D"))
					.flatMap(repository::save);
			peladeiroFlux.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}
}
