package com.marlonpatrick.tacocloud.user;

import java.util.UUID;
import java.util.concurrent.Flow.Publisher;

import com.marlonpatrick.tacocloud.taco.Taco;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveUserRepositoryGateway {//extends CrudRepository
	
	Mono<Taco> findById(UUID id);

	Mono<Taco> findById(Publisher<UUID> id);

	Mono<Boolean> existsById(UUID id);

	Mono<Boolean> existsById(Publisher<UUID> id);

	Flux<Taco> findAll();

	Flux<Taco> findAllById(Iterable<UUID> ids);

	Flux<Taco> findAllById(Publisher<UUID> idStream);

	Mono<Long> count();

	Mono<User> findByUsername(String username);
}

interface FullReactiveUserRepositoryGateway extends ReactiveUserRepositoryGateway{

	<S extends Taco> Mono<S> save(S entity);

	<S extends Taco> Flux<S> saveAll(Iterable<S> entities);

	<S extends Taco> Flux<S> saveAll(Publisher<S> entityStream);

	<S extends Taco> Mono<S> insert(S entity);

	<S extends Taco> Flux<S> insert(Iterable<S> entities);

	<S extends Taco> Flux<S> insert(Publisher<S> entities);

	Mono<Void> deleteById(UUID id);

	Mono<Void> deleteById(Publisher<UUID> id);

	Mono<Void> delete(Taco entity);

	Mono<Void> deleteAll(Iterable<? extends Taco> entities);

	Mono<Void> deleteAll(Publisher<? extends Taco> entityStream);
}
