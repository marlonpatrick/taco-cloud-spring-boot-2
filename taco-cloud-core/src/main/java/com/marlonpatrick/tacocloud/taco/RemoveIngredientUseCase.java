package com.marlonpatrick.tacocloud.taco;

import org.springframework.stereotype.Service;

@Service
class RemoveIngredientUseCase {

	private FullIngredientRepository ingredientRepository;

	public RemoveIngredientUseCase(FullIngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public void execute(String ingredientId){
		this.ingredientRepository.deleteById(ingredientId);
	}
}
