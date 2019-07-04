package com.marlonpatrick.tacocloud.user;

import java.util.Optional;

public interface UserRepositoryGateway {

	public Optional<User> findById(Long id);

	public boolean existsById(Long id);

	public Iterable<User> findAllById(Iterable<Long> ids);

	public long count();

	public User findByUsername(String username);
}

interface FullUserRepositoryGateway extends UserRepositoryGateway{

	public <S extends User> S save(S entity);

	public <S extends User> Iterable<S> saveAll(Iterable<S> entities);

	public void deleteById(Long id);

	public void delete(User entity);

	public void deleteAll(Iterable<? extends User> entities);
}
