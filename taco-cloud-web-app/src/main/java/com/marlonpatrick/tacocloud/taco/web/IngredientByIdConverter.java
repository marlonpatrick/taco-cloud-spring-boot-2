package com.marlonpatrick.tacocloud.taco.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.marlonpatrick.tacocloud.taco.domain.Ingredient;
import com.marlonpatrick.tacocloud.taco.domain.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private IngredientRepository ingredientRepo;

  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }
  
  @Override
  public Ingredient convert(String id) {
    return ingredientRepo.findById(id);
  }

}
