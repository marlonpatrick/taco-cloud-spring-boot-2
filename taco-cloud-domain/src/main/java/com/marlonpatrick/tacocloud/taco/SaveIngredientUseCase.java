package com.marlonpatrick.tacocloud.taco;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
class SaveIngredientUseCase {

	private ReactiveIngredientRepositoryGateway ingredientRepository;

	public SaveIngredientUseCase(ReactiveIngredientRepositoryGateway ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public Mono<Ingredient> execute(Ingredient ingredient){
		return this.ingredientRepository.save(ingredient);
	}
}