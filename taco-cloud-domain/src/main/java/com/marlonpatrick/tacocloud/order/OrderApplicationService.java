package com.marlonpatrick.tacocloud.order;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderApplicationService {

	@Autowired
	private OrderRepositoryGateway orderRepositoryGateway;

	@Autowired
	private OrderMessagingSenderGateway orderMessagingGateway;

	@Autowired
	private SaveOrderUseCase saveOrderUseCase;

	@Autowired
	private RemoveOrderUseCase removeOrderUseCase;

	public Optional<Order> findById(Long id){
		return this.orderRepositoryGateway.findById(id);
	}

	public Order saveOrder(Order order) {
		return this.saveOrderUseCase.execute(order);
	}
	
	public void removeOrder(Long orderId) {
		this.removeOrderUseCase.execute(orderId);
	}
	
	public void sendOrder(Long orderId) {
		Optional<Order> optionalOrder = this.findById(orderId);
		Order order = optionalOrder.get();
		order.setPlacedAt(ZonedDateTime.now());
		order.setTacos(null);//many errors, try resolve posteriorly
//		for (Taco taco : order.getTacos()) {
//			taco.getIngredients();
//		}
		orderMessagingGateway.sendOrder(order);
	}
}
