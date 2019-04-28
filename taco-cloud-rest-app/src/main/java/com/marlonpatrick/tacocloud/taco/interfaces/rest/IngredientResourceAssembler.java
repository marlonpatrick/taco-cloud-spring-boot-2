package com.marlonpatrick.tacocloud.taco.interfaces.rest;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.marlonpatrick.tacocloud.taco.Ingredient;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource>{

	public static final IngredientResourceAssembler INSTANCE = new IngredientResourceAssembler();
	
	private IngredientResourceAssembler() {
		super(IngredientRestController.class, IngredientResource.class);
	}

	@Override
	protected IngredientResource instantiateResource(Ingredient entity) {
		return new IngredientResource(entity);
	}
	
	@Override
	public IngredientResource toResource(Ingredient entity) {
		return createResourceWithId(entity.getId(), entity);
	}

}
