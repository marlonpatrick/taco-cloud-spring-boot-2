package com.marlonpatrick.tacocloud.user;

import org.springframework.data.repository.Repository;

interface JPAUserRepository extends FullUserRepositoryGateway, Repository<User, Long> {

}
