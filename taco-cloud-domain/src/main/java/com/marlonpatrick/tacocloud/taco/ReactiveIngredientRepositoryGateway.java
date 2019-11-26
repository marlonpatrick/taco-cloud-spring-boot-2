package com.marlonpatrick.tacocloud.taco;

import java.util.concurrent.Flow.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

interface ReactiveIngredientRepositoryGateway {//extends ReactiveCrudRepository / ReactiveCassandraRepository

	<S extends Ingredient> Mono<S> save(S entity);

	<S extends Ingredient> Flux<S> saveAll(Iterable<S> entities);

	<S extends Ingredient> Flux<S> saveAll(Publisher<S> entityStream);

	Mono<Ingredient> findById(String id);

	Mono<Ingredient> findById(Publisher<String> id);

	Mono<Boolean> existsById(String id);

	Mono<Boolean> existsById(Publisher<String> id);

	Flux<Ingredient> findAll();
	
	Flux<Ingredient> findAllById(Iterable<String> ids);

	Flux<Ingredient> findAllById(Publisher<String> idStream);

	Mono<Long> count();

	Mono<Void> deleteById(String id);

	Mono<Void> deleteById(Publisher<String> id);

	Mono<Void> delete(Ingredient entity);

	Mono<Void> deleteAll(Iterable<? extends Ingredient> entities);

	Mono<Void> deleteAll(Publisher<? extends Ingredient> entityStream);

	Mono<Void> deleteAll();
	
}
