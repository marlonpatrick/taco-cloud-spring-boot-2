package com.marlonpatrick.tacocloud.taco.rest;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.marlonpatrick.tacocloud.taco.Taco;

import lombok.Getter;

@Relation(value="taco", collectionRelation="tacos")
public class TacoResource extends ResourceSupport {
	
	@Getter
	private final ZonedDateTime createdAt;

	@Getter
	private final String name;

	@Getter
	private final List<IngredientResource> ingredients;

	public TacoResource(Taco taco) {
		this.createdAt = taco.getCreatedAt();
		this.name = taco.getName();
		this.ingredients = IngredientResourceAssembler.INSTANCE.toResources(taco.getIngredients());
	}
}
