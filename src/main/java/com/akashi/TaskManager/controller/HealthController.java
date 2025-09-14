package com.akashi.TaskManager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HealthController {

	@GetMapping("/health")
	public String HelloWorld() {
		return "Hello World!";
	}
}
