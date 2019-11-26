package com.marlonpatrick.tacocloud.taco;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TacoApplicationService {

	@Autowired
	private ReactiveTacoRepositoryGateway tacoRepositoryGateway;

	@Autowired
	private SaveTacoUseCase saveTacoUseCase;

	public Mono<Taco> findById(UUID id){
		return tacoRepositoryGateway.findById(id);
	}

	public Flux<Taco> findAll(Pageable pageable){
		return tacoRepositoryGateway.findAll().take(pageable.getPageSize());
	}

	public Mono<Taco> saveTaco(Taco taco) {
		return this.saveTacoUseCase.execute(taco);
	}
}
