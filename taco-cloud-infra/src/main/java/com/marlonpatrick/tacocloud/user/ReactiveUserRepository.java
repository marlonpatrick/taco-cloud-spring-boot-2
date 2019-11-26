package com.marlonpatrick.tacocloud.user;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.Repository;

import reactor.core.publisher.Mono;

interface ReactiveUserRepository extends FullReactiveUserRepositoryGateway, Repository<User, UUID> {

	@Override
	@AllowFiltering
	Mono<User> findByUsername(String username);
}
