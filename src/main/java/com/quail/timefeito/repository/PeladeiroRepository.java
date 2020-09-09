package com.quail.timefeito.repository;

import com.quail.timefeito.model.Peladeiro;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PeladeiroRepository extends ReactiveMongoRepository <Peladeiro, String>{
}
