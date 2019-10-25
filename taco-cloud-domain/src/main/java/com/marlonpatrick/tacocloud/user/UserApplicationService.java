package com.marlonpatrick.tacocloud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

	@Autowired
	private SaveUserUseCase saveUserUseCase;
		
	public User saveUser(User user) {
		return this.saveUserUseCase.execute(user);
	}
}
