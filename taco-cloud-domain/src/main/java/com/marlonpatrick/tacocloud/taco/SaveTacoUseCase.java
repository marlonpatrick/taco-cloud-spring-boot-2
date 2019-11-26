package com.marlonpatrick.tacocloud.taco;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
class SaveTacoUseCase {

	private ReactiveTacoRepositoryGateway tacoRepository;

	public SaveTacoUseCase(ReactiveTacoRepositoryGateway tacoRepository) {
		this.tacoRepository = tacoRepository;
	}

	public Mono<Taco> execute(Taco taco){
		return this.tacoRepository.save(taco);
	}
}
