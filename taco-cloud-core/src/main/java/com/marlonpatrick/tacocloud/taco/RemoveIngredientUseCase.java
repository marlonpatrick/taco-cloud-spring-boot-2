package com.marlonpatrick.tacocloud.taco;

import org.springframework.stereotype.Service;

@Service
class RemoveIngredientUseCase {

	private IngredientRepositoryGateway ingredientRepository;

	public RemoveIngredientUseCase(IngredientRepositoryGateway ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public void execute(String ingredientId){
		this.ingredientRepository.deleteById(ingredientId);
	}
}
