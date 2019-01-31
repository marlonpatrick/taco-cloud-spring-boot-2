package com.marlonpatrick.tacocloud.order.domain.model;

import java.util.Optional;

public interface OrderRepository {

	<S extends Order> S save(S entity);

	<S extends Order> Iterable<S> saveAll(Iterable<S> entities);

	Optional<Order> findById(Long id);

	boolean existsById(Long id);

	Iterable<Order> findAllById(Iterable<Long> ids);

	long count();

	void deleteById(Long id);
	
	void delete(Order entity);
	
	void deleteAll(Iterable<? extends Order> entities);
}
