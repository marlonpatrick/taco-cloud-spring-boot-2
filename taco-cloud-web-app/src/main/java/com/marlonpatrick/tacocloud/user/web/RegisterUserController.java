package com.marlonpatrick.tacocloud.user.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marlonpatrick.tacocloud.user.domain.model.UserRepository;

@Controller
@RequestMapping("/user/register")
public class RegisterUserController {

	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	public RegisterUserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping
	public String showForm() {
		return "user/registerUser";
	}
	
	@PostMapping
	public String processRegisterUser(RegisterUserForm form) {
		
		this.userRepository.save(form.toUser(this.passwordEncoder));
	    return "redirect:/login";
	}

}
