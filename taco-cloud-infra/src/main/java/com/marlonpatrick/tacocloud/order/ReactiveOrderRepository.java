package com.marlonpatrick.tacocloud.order;

import java.util.UUID;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface ReactiveOrderRepository extends ReactiveOrderRepositoryGateway, Repository<Order, UUID> {

}
