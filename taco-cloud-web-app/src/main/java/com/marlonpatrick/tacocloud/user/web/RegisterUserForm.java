package com.marlonpatrick.tacocloud.user.web;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.marlonpatrick.tacocloud.user.User;

import lombok.Data;

@Data
public class RegisterUserForm {

	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;

	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
	}

}
