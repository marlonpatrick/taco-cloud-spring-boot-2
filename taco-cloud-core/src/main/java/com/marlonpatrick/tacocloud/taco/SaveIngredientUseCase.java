package com.marlonpatrick.tacocloud.taco;

import org.springframework.stereotype.Service;

@Service
class SaveIngredientUseCase {

	private IngredientRepositoryGateway ingredientRepository;

	public SaveIngredientUseCase(IngredientRepositoryGateway ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public Ingredient execute(Ingredient ingredient){
		return this.ingredientRepository.save(ingredient);
	}
}
