package com.marlonpatrick.tacocloud.taco.domain.model;

import java.util.Optional;

public interface IngredientRepository {

	<S extends Ingredient> S save(S entity);

	<S extends Ingredient> Iterable<S> saveAll(Iterable<S> entities);

	Optional<Ingredient> findById(String id);

	boolean existsById(String id);

	Iterable<Ingredient> findAll();

	Iterable<Ingredient> findAllById(Iterable<String> ids);

	long count();

	void deleteById(String id);
	
	void delete(Ingredient entity);
	
	void deleteAll(Iterable<? extends Ingredient> entities);
}
