package com.marlonpatrick.tacocloud.order;

import org.springframework.data.repository.Repository;

interface JPAOrderRepository extends FullOrderRepository, Repository<Order, Long> {
	
}
