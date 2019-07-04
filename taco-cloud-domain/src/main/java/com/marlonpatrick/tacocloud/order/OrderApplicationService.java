package com.marlonpatrick.tacocloud.order;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderApplicationService {

	@Autowired
	private OrderRepositoryGateway orderRepositoryGateway;

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
}
