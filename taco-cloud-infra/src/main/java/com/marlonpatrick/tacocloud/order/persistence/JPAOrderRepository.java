package com.marlonpatrick.tacocloud.order.persistence;

import org.springframework.data.repository.Repository;

import com.marlonpatrick.tacocloud.order.domain.model.Order;
import com.marlonpatrick.tacocloud.order.domain.model.OrderRepository;

interface JPAOrderRepository extends OrderRepository, Repository<Order, Long> {
	
}
