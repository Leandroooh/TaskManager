package com.akashi.TaskManager.controller;


/*
 * Modificadores:
 *   Public
 *   Private
 *   Protected
 *  */

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.akashi.TaskManager.model.UserModel;
import com.akashi.TaskManager.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody UserModel userData) {

		var findUser = this.userRepository.findByUsername(userData.getUsername());

		if(findUser != null){
			return ResponseEntity.unprocessableEntity().body("User already exists!");
		}

		var cryptedPass = BCrypt.withDefaults().hashToString(12, userData.getPassword().toCharArray());
		userData.setPassword(cryptedPass);

		this.userRepository.save(userData);
		return ResponseEntity.status(201).body("User created success" + userData.getId());
	}
}
