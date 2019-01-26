package com.marlonpatrick.tacocloud.order.persistence;

import org.springframework.data.repository.CrudRepository;

import com.marlonpatrick.tacocloud.order.domain.Order;
import com.marlonpatrick.tacocloud.order.domain.OrderRepository;

interface JPAOrderRepository extends OrderRepository, CrudRepository<Order, Long> {

	default Iterable<Order> findAll() {
		throw new RuntimeException("findAll: method not implemented.");
	}

	default void deleteAll() {
		throw new RuntimeException("deleteAll: method not implemented");
	}
}
