package com.akashi.TaskManager.controller;

import com.akashi.TaskManager.model.TaskModel;
import com.akashi.TaskManager.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {


	final TaskRepository taskRepository;

	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createTask(@RequestBody TaskModel taskData){

		taskRepository.save(taskData);
		return ResponseEntity.status(201).body("Task created success!");
	}
}
