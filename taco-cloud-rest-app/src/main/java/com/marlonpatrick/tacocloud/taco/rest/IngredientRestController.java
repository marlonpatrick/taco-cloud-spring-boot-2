package com.marlonpatrick.tacocloud.taco.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marlonpatrick.tacocloud.taco.domain.model.Ingredient;
import com.marlonpatrick.tacocloud.taco.domain.model.IngredientRepository;

@RestController
@RequestMapping(path="/ingredients", produces="application/json")
@CrossOrigin(origins="*")
public class IngredientRestController {

  private IngredientRepository ingredientRepository;

  @Autowired
  public IngredientRestController(IngredientRepository repo) {
    this.ingredientRepository = repo;
  }

  @GetMapping
  public Iterable<Ingredient> allIngredients() {
    return ingredientRepository.findAll();
  }
  
}
