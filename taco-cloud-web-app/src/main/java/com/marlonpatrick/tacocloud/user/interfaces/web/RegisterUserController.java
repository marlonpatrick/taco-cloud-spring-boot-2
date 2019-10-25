package com.marlonpatrick.tacocloud.user.interfaces.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marlonpatrick.tacocloud.user.UserApplicationService;

@Controller
@RequestMapping("/user/register")
public class RegisterUserController {

	private UserApplicationService userApplicationService;

	private PasswordEncoder passwordEncoder;

	public RegisterUserController(UserApplicationService userApplicationService, PasswordEncoder passwordEncoder) {
		this.userApplicationService = userApplicationService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping
	public String showForm() {
		return "user/registerUser";
	}
	
	@PostMapping
	public String processRegisterUser(RegisterUserForm form) {
		
		this.userApplicationService.saveUser(form.toUser(this.passwordEncoder));
	    return "redirect:/login";
	}

}
