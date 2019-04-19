package com.marlonpatrick.tacocloud.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marlonpatrick.tacocloud.user.User;
import com.marlonpatrick.tacocloud.user.UserRepository;

@Service
class TacoCloudUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	TacoCloudUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		return user;
	}

}
