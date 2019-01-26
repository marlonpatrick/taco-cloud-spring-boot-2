package com.marlonpatrick.tacocloud.taco.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient {
	  
	  @Id
	  private final String id;
	  
	  private final String name;
	  
	  @Enumerated(EnumType.STRING)
	  private final IngredientTypes type;
}
