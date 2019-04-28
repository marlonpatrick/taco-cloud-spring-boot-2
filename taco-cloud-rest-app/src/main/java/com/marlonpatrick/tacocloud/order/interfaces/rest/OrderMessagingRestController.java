package com.marlonpatrick.tacocloud.order.interfaces.rest;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marlonpatrick.tacocloud.order.Order;
import com.marlonpatrick.tacocloud.order.OrderMessagingSenderGateway;
import com.marlonpatrick.tacocloud.order.OrderRepositoryGateway;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/orders", produces = "text/plain")
public class OrderMessagingRestController {

	@Autowired
	private OrderRepositoryGateway orderRepository;

//	@Autowired
//	private OrderApplicationService orderApplicationService;

	@Autowired
	private OrderMessagingSenderGateway orderMessagingGateway;

	@GetMapping("/send/{id}")
	@Transactional(readOnly=true)
	public String send(@PathVariable("id") Long id) {
		Optional<Order> optionalOrder = orderRepository.findById(id);
		Order order = optionalOrder.orElse(null);
		order.setPlacedAt(ZonedDateTime.now());
		order.setTacos(null);//many errors, try resolve posteriorly
//		for (Taco taco : order.getTacos()) {
//			taco.getIngredients();
//		}
		orderMessagingGateway.sendOrder(order);
		return "Message sent successfully";
	}
}
