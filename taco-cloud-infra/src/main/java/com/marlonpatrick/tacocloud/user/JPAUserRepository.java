package com.marlonpatrick.tacocloud.user;

import org.springframework.data.repository.Repository;

interface JPAUserRepository extends FullUserRepository, Repository<User, Long> {

}
