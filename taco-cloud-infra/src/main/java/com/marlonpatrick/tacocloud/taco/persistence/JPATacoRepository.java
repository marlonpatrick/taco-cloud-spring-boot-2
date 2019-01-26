package com.marlonpatrick.tacocloud.taco.persistence;

import org.springframework.data.repository.CrudRepository;

import com.marlonpatrick.tacocloud.taco.domain.Taco;
import com.marlonpatrick.tacocloud.taco.domain.TacoRepository;

interface JPATacoRepository extends TacoRepository, CrudRepository<Taco, Long> {

	default Iterable<Taco> findAll() {
		throw new RuntimeException("findAll: method not implemented.");
	}

	default void deleteAll() {
		throw new RuntimeException("deleteAll: method not implemented");
	}

}
