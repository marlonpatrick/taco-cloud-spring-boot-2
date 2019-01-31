package com.marlonpatrick.tacocloud.taco.domain.model;

import java.util.Optional;

public interface TacoRepository {

	<S extends Taco> S save(S entity);

	<S extends Taco> Iterable<S> saveAll(Iterable<S> entities);

	Optional<Taco> findById(Long id);

	boolean existsById(Long id);

	Iterable<Taco> findAllById(Iterable<Long> ids);

	long count();

	void deleteById(Long id);

	void delete(Taco entity);

	void deleteAll(Iterable<? extends Taco> entities);
}
