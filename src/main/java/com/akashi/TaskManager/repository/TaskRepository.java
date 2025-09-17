package com.akashi.TaskManager.repository;

import com.akashi.TaskManager.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
	List<TaskModel> findByUserID(UUID user);
}
