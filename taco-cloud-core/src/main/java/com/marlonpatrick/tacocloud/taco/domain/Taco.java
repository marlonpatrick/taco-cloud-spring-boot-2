package com.marlonpatrick.tacocloud.taco.domain;

import java.time.ZonedDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {

	private Long id;

	private ZonedDateTime createdAt;

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;
}
