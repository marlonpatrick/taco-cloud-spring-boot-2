package com.marlonpatrick.tacocloud.order;

import org.springframework.stereotype.Service;

@Service
class RemoveOrderUseCase {

	private FullOrderRepositoryGateway orderRepository;

	public RemoveOrderUseCase(FullOrderRepositoryGateway orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void execute(Long orderId){
		this.orderRepository.deleteById(orderId);
	}
}
