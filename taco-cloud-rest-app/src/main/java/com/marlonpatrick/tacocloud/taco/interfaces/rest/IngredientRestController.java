package com.marlonpatrick.tacocloud.taco.interfaces.rest;

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

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientRestController {

	@Autowired
	private IngredientApplicationService ingredientApplicationService;

	@GetMapping("/{id}")
	public Mono<Ingredient> byId(@PathVariable("id") String id) {
		//TODO: test reactive
		return ingredientApplicationService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Ingredient> create(@RequestBody Ingredient ingredient) {
		//TODO: test reactive
		return ingredientApplicationService.saveIngredient(ingredient);
	}
	
	@PutMapping("/{id}")
	public void update(@RequestBody Ingredient ingredient) {
		//TODO: return Mono?
		ingredientApplicationService.saveIngredient(ingredient);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		//TODO: return Mono?
		ingredientApplicationService.removeIngredient(id);
	}
}
