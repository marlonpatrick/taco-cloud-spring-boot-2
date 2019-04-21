package com.marlonpatrick.tacocloud;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.marlonpatrick.tacocloud.taco.Ingredient;
import com.marlonpatrick.tacocloud.taco.IngredientTypes;

public class SpringRestTemplateClientTest {

	static final RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		System.out.println(getIngredientById("XPTO"));
		deleteIngredientById("XPTO");
		System.out.println(getIngredientByIdWithMapParameters("XPTO"));
		insertIngredient();
		System.out.println(getIngredientByIdWithURI("XPTO"));
		updateIngredient();
		System.out.println(getIngredientByIdUsingForEntity("XPTO"));
	}

	static void deleteIngredientById(String ingredientId) {
		restTemplate.delete("http://localhost:8080/ingredients/{id}", ingredientId);
	}

	static void updateIngredient() {
		
		Ingredient ingredient = new Ingredient("XPTO", "Ingredient XPTO Updated", IngredientTypes.PROTEIN);

//		Ingredient ingredientBefore = getIngredientById(ingredient.getId());

		restTemplate.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
		
//		Ingredient ingredientAfter = getIngredientById(ingredient.getId());
		
//		System.out.println(ingredientBefore);		
//		System.out.println(ingredientAfter);
	}

	static Ingredient insertIngredient() {
		
		Ingredient ingredient = new Ingredient("XPTO", "Ingredient XPTO", IngredientTypes.PROTEIN);
		
		URI postForLocation = restTemplate.postForLocation("http://localhost:8080/ingredients", ingredient, Ingredient.class);
		
		System.out.println(postForLocation);
		
		return ingredient;
//		
//		System.out.println(ingredient);		
//		System.out.println(returnedIngredient);
	}

	static Ingredient getIngredientByIdUsingForEntity(String ingredientId) {
		ResponseEntity<Ingredient> ingredientEntity = restTemplate.getForEntity("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
		
		System.out.println(ingredientEntity.getHeaders().getDate());		
		
		return ingredientEntity.getBody();
	}

	static Ingredient getIngredientByIdWithURI(String ingredientId) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("id", ingredientId);

		URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/ingredients/{id}").build(parameters);

		return restTemplate.getForObject(uri, Ingredient.class);
	}

	static Ingredient getIngredientByIdWithMapParameters(String ingredientId) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("id", ingredientId);

		return restTemplate.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, parameters);
	}

	static Ingredient getIngredientById(String ingredientId) {
		return restTemplate.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
	}
}
