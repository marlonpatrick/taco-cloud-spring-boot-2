package com.marlonpatrick.tacocloud.order;

import org.springframework.stereotype.Service;

@Service
class RemoveOrderUseCase {

	private FullOrderRepository orderRepository;

	public RemoveOrderUseCase(FullOrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void execute(Long orderId){
		this.orderRepository.deleteById(orderId);
	}
}
