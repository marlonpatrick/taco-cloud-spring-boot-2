package com.marlonpatrick.tacocloud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserApplicationService {

	@Autowired
	private SaveUserUseCase saveUserUseCase;
		
	public Mono<User> saveUser(User user) {
		return this.saveUserUseCase.execute(user);
	}
}
