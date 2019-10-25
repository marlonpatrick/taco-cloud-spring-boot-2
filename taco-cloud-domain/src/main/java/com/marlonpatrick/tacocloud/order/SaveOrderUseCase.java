package com.marlonpatrick.tacocloud.order;

import org.springframework.stereotype.Service;

@Service
class SaveOrderUseCase {

	private OrderRepositoryGateway orderRepository;

	public SaveOrderUseCase(OrderRepositoryGateway orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order execute(Order order){
		return this.orderRepository.save(order);
	}
}
