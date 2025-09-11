package com.akashi.TaskManager.controller;


/*
 * Modificadores:
 *   Public
 *   Private
 *   Protected
 *  */

import com.akashi.TaskManager.model.UserModel;
import com.akashi.TaskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/create")
	public UserModel createUser(@RequestBody UserModel userData) {
		return this.userRepository.save(userData);
	}
}
