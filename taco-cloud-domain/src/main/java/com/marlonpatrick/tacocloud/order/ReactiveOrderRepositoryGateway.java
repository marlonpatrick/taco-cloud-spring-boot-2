package com.marlonpatrick.tacocloud.order;

import java.util.UUID;
import java.util.concurrent.Flow.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

interface ReactiveOrderRepositoryGateway {////extends ReactiveCrudRepository / ReactiveCassandraRepository

	<S extends Order> Mono<S> save(S entity);

	<S extends Order> Flux<S> saveAll(Iterable<S> entities);

	<S extends Order> Flux<S> saveAll(Publisher<S> entityStream);

	<S extends Order> Mono<S> insert(S entity);

	<S extends Order> Flux<S> insert(Iterable<S> entities);

	<S extends Order> Flux<S> insert(Publisher<S> entities);

	Mono<Order> findById(UUID id);

	Mono<Order> findById(Publisher<UUID> id);

	Mono<Boolean> existsById(UUID id);

	Mono<Boolean> existsById(Publisher<UUID> id);

	Flux<Order> findAll();

	Flux<Order> findAllById(Iterable<UUID> ids);

	Flux<Order> findAllById(Publisher<UUID> idStream);

	Mono<Long> count();

	Mono<Void> deleteById(UUID id);

	Mono<Void> deleteById(Publisher<UUID> id);

	Mono<Void> delete(Order entity);

	Mono<Void> deleteAll(Iterable<? extends Order> entities);

	Mono<Void> deleteAll(Publisher<? extends Order> entityStream);
}
