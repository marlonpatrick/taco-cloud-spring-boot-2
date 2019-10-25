package com.marlonpatrick.tacocloud.order.interfaces.web;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.marlonpatrick.tacocloud.order.Order;
import com.marlonpatrick.tacocloud.order.OrderApplicationService;
import com.marlonpatrick.tacocloud.user.User;

@Controller
@RequestMapping("/order/place")
@SessionAttributes("order")
public class PlaceOrderController {
	
	private OrderApplicationService orderApplicationService;

	public PlaceOrderController(OrderApplicationService orderApplicationService) {
		this.orderApplicationService = orderApplicationService;
	}

	@GetMapping("/current")
	public String showForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {

		if (order.getDeliveryName() == null) {
			order.setDeliveryName(user.getFullname());
		}
		if (order.getDeliveryStreet() == null) {
			order.setDeliveryStreet(user.getStreet());
		}
		if (order.getDeliveryCity() == null) {
			order.setDeliveryCity(user.getCity());
		}
		if (order.getDeliveryState() == null) {
			order.setDeliveryState(user.getState());
		}
		if (order.getDeliveryZip() == null) {
			order.setDeliveryZip(user.getZip());
		}

		return "order/placeOrder";
	}

	@PostMapping
	public String processPlaceOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
			@AuthenticationPrincipal User user) {

		if (errors.hasErrors()) {
			return "order/placeOrder";
		}

		order.setUser(user);

		this.orderApplicationService.saveOrder(order);

		sessionStatus.setComplete();

		return "redirect:/";
	}
}
