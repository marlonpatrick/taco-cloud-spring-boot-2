package com.marlonpatrick.tacocloud.taco.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.marlonpatrick.tacocloud.taco.Ingredient;
import com.marlonpatrick.tacocloud.taco.IngredientApplicationService;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private IngredientApplicationService ingredientApplicationService;

  @Autowired
  public IngredientByIdConverter(IngredientApplicationService ingredientApplicationService) {
    this.ingredientApplicationService = ingredientApplicationService;
  }
  
  @Override
  public Ingredient convert(String id) {
    return this.ingredientApplicationService.findById(id).orElse(null);
  }

}
