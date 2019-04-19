package com.marlonpatrick.tacocloud.order;

import org.springframework.stereotype.Service;

@Service
class SaveOrderUseCase {

	private FullOrderRepository orderRepository;

	public SaveOrderUseCase(FullOrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order execute(Order order){
		return this.orderRepository.save(order);
	}
}
