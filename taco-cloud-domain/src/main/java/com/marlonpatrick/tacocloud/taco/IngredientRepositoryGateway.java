package com.marlonpatrick.tacocloud.taco;

import java.util.Optional;

interface IngredientRepositoryGateway {

	Optional<Ingredient> findById(String id);

	boolean existsById(String id);

	Iterable<Ingredient> findAll();

	Iterable<Ingredient> findAllById(Iterable<String> ids);

	long count();
	
	<S extends Ingredient> S save(S entity);

	<S extends Ingredient> Iterable<S> saveAll(Iterable<S> entities);

	void deleteById(String id);
	
	void delete(Ingredient entity);
	
	void deleteAll(Iterable<? extends Ingredient> entities);
}
