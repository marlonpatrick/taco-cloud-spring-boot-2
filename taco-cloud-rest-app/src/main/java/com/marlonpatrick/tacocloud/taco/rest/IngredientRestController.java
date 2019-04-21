package com.marlonpatrick.tacocloud.taco.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marlonpatrick.tacocloud.taco.Ingredient;
import com.marlonpatrick.tacocloud.taco.IngredientApplicationService;
import com.marlonpatrick.tacocloud.taco.IngredientRepository;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientRestController {

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private IngredientApplicationService ingredientApplicationService;

	@GetMapping
	public Iterable<Ingredient> allIngredients() {
		return ingredientRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Ingredient> byId(@PathVariable("id") String id) {
		return ingredientRepository.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ingredient create(@RequestBody Ingredient ingredient) {
		return ingredientApplicationService.saveIngredient(ingredient);
	}
	
	@PutMapping("/{id}")
	public void update(@RequestBody Ingredient ingredient) {
		ingredientApplicationService.saveIngredient(ingredient);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		ingredientApplicationService.removeIngredient(id);
	}
}
