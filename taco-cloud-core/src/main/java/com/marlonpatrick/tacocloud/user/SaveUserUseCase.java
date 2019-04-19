package com.marlonpatrick.tacocloud.user;

import org.springframework.stereotype.Service;

@Service
class SaveUserUseCase {

	private FullUserRepository userRepository;

	public SaveUserUseCase(FullUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User execute(User user){
		return this.userRepository.save(user);
	}
}
