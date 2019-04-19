package com.marlonpatrick.tacocloud.taco;

import org.springframework.data.repository.Repository;

interface JPAIngredientRepository extends FullIngredientRepository, Repository<Ingredient, String> {

}
