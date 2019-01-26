package com.marlonpatrick.tacocloud.taco.persistence;

import org.springframework.data.repository.CrudRepository;

import com.marlonpatrick.tacocloud.taco.domain.Ingredient;
import com.marlonpatrick.tacocloud.taco.domain.IngredientRepository;

interface JPAIngredientRepository extends IngredientRepository, CrudRepository<Ingredient, String> {

	@Override
	default void deleteAll() {
		throw new RuntimeException("deleteAll: method not implemented");
	}

}
