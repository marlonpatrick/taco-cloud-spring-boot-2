package com.marlonpatrick.tacocloud.taco.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.marlonpatrick.tacocloud.order.domain.Order;
import com.marlonpatrick.tacocloud.taco.domain.Ingredient;
import com.marlonpatrick.tacocloud.taco.domain.IngredientRepository;
import com.marlonpatrick.tacocloud.taco.domain.IngredientTypes;
import com.marlonpatrick.tacocloud.taco.domain.Taco;
import com.marlonpatrick.tacocloud.taco.domain.TacoRepository;

@Controller
@RequestMapping("/taco/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepository;

	private final TacoRepository tacoRepository;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showForm(Model model) {

		List<Ingredient> ingredients = new ArrayList<>();

		this.ingredientRepository.findAll().forEach(i -> ingredients.add(i));

		IngredientTypes[] types = IngredientTypes.values();

		for (IngredientTypes type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}

		return "taco/designTaco";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, IngredientTypes type) {
		return ingredients.stream().filter(i -> i.getType().equals(type)).collect(Collectors.toList());
	}

	@PostMapping
	public String processDesignTaco(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {

		if (errors.hasErrors()) {
			return "taco/designTaco";
		}

		Taco saved = this.tacoRepository.save(taco);
		order.addTaco(saved);

		return "redirect:/order/place/current";
	}

}
