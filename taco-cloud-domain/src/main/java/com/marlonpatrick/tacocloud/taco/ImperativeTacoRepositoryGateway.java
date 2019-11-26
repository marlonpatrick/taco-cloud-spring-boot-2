package com.marlonpatrick.tacocloud.taco;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface ImperativeTacoRepositoryGateway {//extends CrudRepository

	Optional<Taco> findById(Long id);

	boolean existsById(Long id);

	Iterable<Taco> findAllById(Iterable<Long> ids);

	long count();
		
	Page<Taco> findAllWithIngredients(Pageable pageable);
	
	<S extends Taco> S save(S entity);

	<S extends Taco> Iterable<S> saveAll(Iterable<S> entities);

	void deleteById(Long id);

	void delete(Taco entity);

	void deleteAll(Iterable<? extends Taco> entities);
}
