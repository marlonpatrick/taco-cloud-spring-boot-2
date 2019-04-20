package com.marlonpatrick.tacocloud.order;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface JPAOrderRepository extends FullOrderRepository, Repository<Order, Long> {
	
}
