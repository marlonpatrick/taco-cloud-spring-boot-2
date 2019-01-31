package com.marlonpatrick.tacocloud.taco.persistence;

import org.springframework.data.repository.Repository;

import com.marlonpatrick.tacocloud.taco.domain.model.Ingredient;
import com.marlonpatrick.tacocloud.taco.domain.model.IngredientRepository;

interface JPAIngredientRepository extends IngredientRepository, Repository<Ingredient, String> {

}
