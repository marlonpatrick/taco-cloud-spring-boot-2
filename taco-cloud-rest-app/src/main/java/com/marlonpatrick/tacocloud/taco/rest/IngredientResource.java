package com.marlonpatrick.tacocloud.taco.rest;

import org.springframework.hateoas.ResourceSupport;

import com.marlonpatrick.tacocloud.taco.Ingredient;
import com.marlonpatrick.tacocloud.taco.IngredientTypes;

import lombok.Getter;

public class IngredientResource extends ResourceSupport {

	@Getter
	private final String name;

	@Getter
	private final IngredientTypes type;

	public IngredientResource(Ingredient ingredient) {
		this.name = ingredient.getName();
		this.type = ingredient.getType();
	}
}
