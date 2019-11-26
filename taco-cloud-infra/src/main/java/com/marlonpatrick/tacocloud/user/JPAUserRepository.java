package com.marlonpatrick.tacocloud.user;

import org.springframework.data.repository.Repository;

interface JPAUserRepository extends FullImperativeUserRepositoryGateway, Repository<User, Long> {

}
