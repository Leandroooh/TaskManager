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
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> createUser(@RequestBody UserModel userData) {

		var findUser = this.userRepository.findByUsername(userData.getUsername());

		if(findUser != null){
			return ResponseEntity.unprocessableEntity().body("User already exists!");
		}

		var createdUser = this.userRepository.save(userData);
		return ResponseEntity.status(201).body("User created success");
	}
}
