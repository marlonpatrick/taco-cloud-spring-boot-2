package com.marlonpatrick.tacocloud.order.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.marlonpatrick.tacocloud.order.domain.Order;
import com.marlonpatrick.tacocloud.order.domain.OrderRepository;


@Controller
@RequestMapping("/order/place")
@SessionAttributes("order")
public class PlaceOrderController {
	
	private OrderRepository orderRepository;
	
	public PlaceOrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@GetMapping("/current")
	public String showForm(Model model){
		return "order/placeOrder";
	}
	
	
	@PostMapping
	public String processPlaceOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus){
		
		if(errors.hasErrors()) {
			return "order/placeOrder";			
		}
		
		this.orderRepository.save(order);
		
		sessionStatus.setComplete();
		
		return "redirect:/";
	}
}
