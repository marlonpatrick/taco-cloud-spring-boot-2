package com.marlonpatrick.tacocloud.taco;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Primary
@RepositoryRestResource
interface ReactiveIngredientRepository extends ReactiveIngredientRepositoryGateway, Repository<Ingredient, String> {

}
