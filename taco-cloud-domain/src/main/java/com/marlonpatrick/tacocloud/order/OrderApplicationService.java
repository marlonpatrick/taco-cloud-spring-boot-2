package com.marlonpatrick.tacocloud.order;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class OrderApplicationService {

	@Autowired
	private ReactiveOrderRepositoryGateway orderRepositoryGateway;

	@Autowired
	private OrderMessagingSenderGateway orderMessagingGateway;

	@Autowired
	private SaveOrderUseCase saveOrderUseCase;

	@Autowired
	private RemoveOrderUseCase removeOrderUseCase;

	public Mono<Order> findById(UUID id){
		return this.orderRepositoryGateway.findById(id);
	}

	public Mono<Order> saveOrder(Order order) {
		return this.saveOrderUseCase.execute(order);
	}
	
	public Mono<Void> removeOrder(UUID orderId) {
		return this.removeOrderUseCase.execute(orderId);
	}
	
	public void sendOrder(UUID orderId) {
		//TODO: implement messaging reactively
		orderMessagingGateway.sendOrder(this.findById(orderId).block());
	}
}
