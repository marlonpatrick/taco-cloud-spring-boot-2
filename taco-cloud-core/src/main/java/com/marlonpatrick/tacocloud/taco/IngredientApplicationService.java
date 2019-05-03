package com.marlonpatrick.tacocloud.taco;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientApplicationService {

	@Autowired
	private IngredientRepositoryGateway ingredientRepositoryGateway;

	@Autowired
	private SaveIngredientUseCase saveIngredientUseCase;
		
	@Autowired
	private RemoveIngredientUseCase removeIngredientUseCase;
	
	public Optional<Ingredient> findById(String id){
		return ingredientRepositoryGateway.findById(id);
	}

	public Iterable<Ingredient> findAll(){
		return ingredientRepositoryGateway.findAll();
	}

	public Ingredient saveIngredient(Ingredient ingredient) {
		return this.saveIngredientUseCase.execute(ingredient);
	}
	
	public void removeIngredient(String ingredientId) {
		this.removeIngredientUseCase.execute(ingredientId);
	}
}
