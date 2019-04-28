package com.marlonpatrick.tacocloud.taco;

import org.springframework.stereotype.Service;

@Service
class RemoveIngredientUseCase {

	private FullIngredientRepositoryGateway ingredientRepository;

	public RemoveIngredientUseCase(FullIngredientRepositoryGateway ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public void execute(String ingredientId){
		this.ingredientRepository.deleteById(ingredientId);
	}
}
