package com.marlonpatrick.tacocloud.order;

import java.util.UUID;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
class RemoveOrderUseCase {

	private ReactiveOrderRepositoryGateway orderRepository;

	public RemoveOrderUseCase(ReactiveOrderRepositoryGateway orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Mono<Void> execute(UUID orderId){
		return this.orderRepository.deleteById(orderId);
	}
}
