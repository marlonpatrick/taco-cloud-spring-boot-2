package com.marlonpatrick.tacocloud.taco.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.marlonpatrick.tacocloud.taco.Ingredient;
import com.marlonpatrick.tacocloud.taco.IngredientRepositoryGateway;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private IngredientRepositoryGateway ingredientRepository;

  @Autowired
  public IngredientByIdConverter(IngredientRepositoryGateway ingredientRepo) {
    this.ingredientRepository = ingredientRepo;
  }
  
  @Override
  public Ingredient convert(String id) {
    return this.ingredientRepository.findById(id).orElse(null);
  }

}
