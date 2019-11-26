package com.marlonpatrick.tacocloud.order;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
class SaveOrderUseCase {

	private ReactiveOrderRepositoryGateway orderRepository;

	public SaveOrderUseCase(ReactiveOrderRepositoryGateway orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Mono<Order> execute(Order order){
		return this.orderRepository.save(order);
	}
}
