package com.marlonpatrick.tacocloud.taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IngredientApplicationService {

	@Autowired
	private ReactiveIngredientRepositoryGateway ingredientRepositoryGateway;

	@Autowired
	private SaveIngredientUseCase saveIngredientUseCase;
		
	@Autowired
	private RemoveIngredientUseCase removeIngredientUseCase;
	
	public Mono<Ingredient> findById(String id){
		return ingredientRepositoryGateway.findById(id);
	}

	public Flux<Ingredient> findAll(){
		return ingredientRepositoryGateway.findAll();
	}

	public Mono<Ingredient> saveIngredient(Ingredient ingredient) {
		return this.saveIngredientUseCase.execute(ingredient);
	}
	
	public Mono<Void> removeIngredient(String ingredientId) {
		return this.removeIngredientUseCase.execute(ingredientId);
	}
}