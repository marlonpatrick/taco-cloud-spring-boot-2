package com.marlonpatrick.tacocloud.user;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
class SaveUserUseCase {

	private FullReactiveUserRepositoryGateway userRepository;

	public SaveUserUseCase(FullReactiveUserRepositoryGateway userRepository) {
		this.userRepository = userRepository;
	}

	public Mono<User> execute(User user){
		return this.userRepository.save(user);
	}
}
