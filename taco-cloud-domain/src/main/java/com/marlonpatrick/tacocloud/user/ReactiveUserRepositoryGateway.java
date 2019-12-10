package com.marlonpatrick.tacocloud.user;

import java.util.UUID;
import java.util.concurrent.Flow.Publisher;

import com.marlonpatrick.tacocloud.taco.Taco;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveUserRepositoryGateway {//extends CrudRepository
	
	Mono<User> findById(UUID id);

	Mono<User> findById(Publisher<UUID> id);

	Mono<Boolean> existsById(UUID id);

	Mono<Boolean> existsById(Publisher<UUID> id);

	Flux<User> findAll();

	Flux<User> findAllById(Iterable<UUID> ids);

	Flux<User> findAllById(Publisher<UUID> idStream);

	Mono<Long> count();

	Mono<User> findByUsername(String username);
}

interface FullReactiveUserRepositoryGateway extends ReactiveUserRepositoryGateway{

	<S extends User> Mono<S> save(S entity);

	<S extends User> Flux<S> saveAll(Iterable<S> entities);

	<S extends User> Flux<S> saveAll(Publisher<S> entityStream);

	<S extends User> Mono<S> insert(S entity);

	<S extends User> Flux<S> insert(Iterable<S> entities);

	<S extends User> Flux<S> insert(Publisher<S> entities);

	Mono<Void> deleteById(UUID id);

	Mono<Void> deleteById(Publisher<UUID> id);

	Mono<Void> delete(User entity);

	Mono<Void> deleteAll(Iterable<? extends User> entities);

	Mono<Void> deleteAll(Publisher<? extends User> entityStream);
}
