package com.marlonpatrick.tacocloud.taco;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
class RemoveIngredientUseCase {

	private ReactiveIngredientRepositoryGateway ingredientRepository;

	public RemoveIngredientUseCase(ReactiveIngredientRepositoryGateway ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public Mono<Void> execute(String ingredientId){
		 return this.ingredientRepository.deleteById(ingredientId);
	}
}
