package com.marlonpatrick.tacocloud.user.persistence;

import org.springframework.data.repository.Repository;

import com.marlonpatrick.tacocloud.user.domain.model.User;
import com.marlonpatrick.tacocloud.user.domain.model.UserRepository;

public interface JPAUserRepository extends UserRepository, Repository<User, Long> {

}
