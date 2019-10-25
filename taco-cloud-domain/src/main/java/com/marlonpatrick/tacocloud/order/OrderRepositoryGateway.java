package com.marlonpatrick.tacocloud.order;

import java.util.Optional;

interface OrderRepositoryGateway {

	Optional<Order> findById(Long id);

	boolean existsById(Long id);

	Iterable<Order> findAll();

	Iterable<Order> findAllById(Iterable<Long> ids);

	long count();
	
	<S extends Order> S save(S entity);

	<S extends Order> Iterable<S> saveAll(Iterable<S> entities);

	void deleteById(Long id);
	
	void delete(Order entity);
	
	void deleteAll(Iterable<? extends Order> entities);
}
