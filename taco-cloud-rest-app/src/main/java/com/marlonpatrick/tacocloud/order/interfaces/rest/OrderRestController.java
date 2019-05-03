package com.marlonpatrick.tacocloud.order.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marlonpatrick.tacocloud.order.Order;
import com.marlonpatrick.tacocloud.order.OrderApplicationService;
import com.marlonpatrick.tacocloud.taco.Ingredient;
import com.marlonpatrick.tacocloud.taco.Taco;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/orders", produces = "application/json")
public class OrderRestController {

	@Autowired
	private OrderApplicationService orderApplicationService;

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Order postOrder(@RequestBody Order order) {
		return orderApplicationService.saveOrder(order);
	}


	@PostMapping(path = "fromEmail", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)//fake
	@Transactional(readOnly=true)
	public Order postOrderFromEmail(@RequestBody String emailOrder) {
		
		System.out.println("EmailOrder\n" + emailOrder);
		
		Order order = orderApplicationService.findById(2L).orElse(null);
		
		for (Taco taco : order.getTacos()) {
			for (Ingredient ingredient : taco.getIngredients()) {
				System.out.println(ingredient.getName());
			}
		}
		
		return order;
	}

	@PutMapping(path = "/{orderId}", consumes = "application/json")
	public Order putOrder(@RequestBody Order order) {
		return orderApplicationService.saveOrder(order);
	}

	@PatchMapping(path = "/{orderId}", consumes = "application/json")
	public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patchOrder) {

		Order order = orderApplicationService.findById(orderId).get();

		if (patchOrder.getDeliveryName() != null) {
			order.setDeliveryName(patchOrder.getDeliveryName());
		}
		if (patchOrder.getDeliveryStreet() != null) {
			order.setDeliveryStreet(patchOrder.getDeliveryStreet());
		}
		if (patchOrder.getDeliveryCity() != null) {
			order.setDeliveryCity(patchOrder.getDeliveryCity());
		}
		if (patchOrder.getDeliveryState() != null) {
			order.setDeliveryState(patchOrder.getDeliveryState());
		}
		if (patchOrder.getDeliveryZip() != null) {
			order.setDeliveryZip(patchOrder.getDeliveryState());
		}
		if (patchOrder.getCcNumber() != null) {
			order.setCcNumber(patchOrder.getCcNumber());
		}
		if (patchOrder.getCcExpiration() != null) {
			order.setCcExpiration(patchOrder.getCcExpiration());
		}
		if (patchOrder.getCcCVV() != null) {
			order.setCcCVV(patchOrder.getCcCVV());
		}

		return orderApplicationService.saveOrder(order);

	}

	@DeleteMapping("/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeOrder(@PathVariable("orderId") Long orderId) {
		try {
			orderApplicationService.removeOrder(orderId);
		} catch (EmptyResultDataAccessException ex) {
			//
		}
	}
}
