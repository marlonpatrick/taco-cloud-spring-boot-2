package com.marlonpatrick.tacocloud.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marlonpatrick.tacocloud.user.ReactiveUserRepositoryGateway;
import com.marlonpatrick.tacocloud.user.User;

@Service
class TacoCloudUserDetailsService implements UserDetailsService {

	private ReactiveUserRepositoryGateway userRepository;

	@Autowired
	TacoCloudUserDetailsService(ReactiveUserRepositoryGateway userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//TODO: implement reactively
		User user = this.userRepository.findByUsername(username).block();

		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		return user;
	}

}
