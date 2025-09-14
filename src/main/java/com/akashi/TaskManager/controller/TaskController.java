package com.akashi.TaskManager.controller;

import com.akashi.TaskManager.model.TaskModel;
import com.akashi.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@PostMapping("/create")
	public ResponseEntity<String> createTask(@RequestBody TaskModel taskData){

		taskRepository.save(taskData);
		return ResponseEntity.status(201).body("Task created success!");
	}
}
