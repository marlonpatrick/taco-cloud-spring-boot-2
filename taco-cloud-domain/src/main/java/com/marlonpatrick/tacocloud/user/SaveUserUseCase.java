package com.marlonpatrick.tacocloud.user;

import org.springframework.stereotype.Service;

@Service
class SaveUserUseCase {

	private FullUserRepositoryGateway userRepository;

	public SaveUserUseCase(FullUserRepositoryGateway userRepository) {
		this.userRepository = userRepository;
	}

	public User execute(User user){
		return this.userRepository.save(user);
	}
}
