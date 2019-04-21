package com.marlonpatrick.tacocloud.taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientApplicationService {

	@Autowired
	private SaveIngredientUseCase saveIngredientUseCase;
		
	@Autowired
	private RemoveIngredientUseCase removeIngredientUseCase;
	
	
	public Ingredient saveIngredient(Ingredient ingredient) {
		return this.saveIngredientUseCase.execute(ingredient);
	}
	
	public void removeIngredient(String ingredientId) {
		this.removeIngredientUseCase.execute(ingredientId);
	}
}
