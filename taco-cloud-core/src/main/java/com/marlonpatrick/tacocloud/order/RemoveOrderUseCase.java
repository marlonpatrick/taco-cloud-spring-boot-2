package com.marlonpatrick.tacocloud.order;

import org.springframework.stereotype.Service;

@Service
class RemoveOrderUseCase {

	private OrderRepositoryGateway orderRepository;

	public RemoveOrderUseCase(OrderRepositoryGateway orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void execute(Long orderId){
		this.orderRepository.deleteById(orderId);
	}
}
