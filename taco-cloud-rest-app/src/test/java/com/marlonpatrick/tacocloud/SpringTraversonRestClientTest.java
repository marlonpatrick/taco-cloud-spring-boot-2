package com.marlonpatrick.tacocloud;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

import com.marlonpatrick.tacocloud.taco.Ingredient;
import com.marlonpatrick.tacocloud.taco.IngredientTypes;
import com.marlonpatrick.tacocloud.taco.Taco;

public class SpringTraversonRestClientTest {

	static final RestTemplate restTemplate = new RestTemplate();

	static final Traverson traverson = new Traverson(URI.create("http://localhost:8080/data-rest"),
			MediaTypes.HAL_JSON);

	public static void main(String[] args) {
		addIngredient();
	}

	static Ingredient addIngredient() {
		Ingredient ingredient = new Ingredient("TRVR", "Ingredient Traverson", IngredientTypes.CHEESE);
		String ingredientsUrl = traverson.follow("ingredients").asLink().getHref();
		return restTemplate.postForObject(ingredientsUrl, ingredient, Ingredient.class);
	}

	static void getRecentTacos() {
		ParameterizedTypeReference<Resources<Taco>> tacoType = new ParameterizedTypeReference<>() {
		};

		// exception: zoneddatetime
		Resources<Taco> tacoResources = traverson.follow("tacos").follow("recents").toObject(tacoType);

		System.out.println(tacoResources.getContent());
	}

	static void getAllIngredients() {
		ParameterizedTypeReference<Resources<Ingredient>> ingredientType = new ParameterizedTypeReference<>() {
		};

		Resources<Ingredient> ingredientResources = traverson.follow("ingredients").toObject(ingredientType);

		System.out.println(ingredientResources.getContent());
	}
}
