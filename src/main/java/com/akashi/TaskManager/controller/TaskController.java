package com.akashi.TaskManager.controller;

import com.akashi.TaskManager.model.TaskModel;
import com.akashi.TaskManager.repository.TaskRepository;
import com.akashi.TaskManager.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {


	final TaskRepository taskRepository;

	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@PostMapping("/create")
	public ResponseEntity createTask(@RequestBody TaskModel taskData, HttpServletRequest request){

		var userTaskID = request.getAttribute("userID");
		taskData.setUserID((UUID) userTaskID);

		var currentDate = LocalDateTime.now();

		if (currentDate.isAfter(taskData.getStartAt()) || currentDate.isAfter(taskData.getEndAt())){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The startAt and endAt date is invalid");
		}

		if (taskData.getStartAt().isAfter(taskData.getEndAt())){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The endAt date is after startAt Date");
		}

		var task = taskRepository.save(taskData);
		return ResponseEntity.status(201).body(task);
	}

	@GetMapping("/")
	public List<TaskModel> listTask(HttpServletRequest request) {
		var userID = request.getAttribute("userID");
		return this.taskRepository.findByUserID((UUID) userID);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity updateTask(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
		var task = taskRepository.findById(id).orElse(null);

		if (task == null){
			return ResponseEntity.status(400).body("User don't have this task available!");
		}

		var userID = request.getAttribute("userID");
		if (!task.getUserID().equals(userID)){
			return ResponseEntity.status(400).body("User don't have this task available!");
		}

		Utils.copyNonNullProperties(taskModel, task);

		var taskUpdate = this.taskRepository.save(task);
		return ResponseEntity.ok().body(this.taskRepository.save(taskUpdate));
	}
}
