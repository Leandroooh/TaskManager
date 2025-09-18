package com.akashi.TaskManager.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
	@Id
	@GeneratedValue
	private UUID id;

	@Column(length = 50)
	private String title;
	private String priority;
	private String description;

	private LocalDateTime startAt;
	private LocalDateTime endAt;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@JoinColumn(name = "user_id")
	private UUID userID;

	// Error Exception
	public void setTitle(String title) throws Exception {
		if(title.length() > 50) {
			throw new Exception("The length limit for the title is 50 characters!");
		}
		this.title = title;
	}
}
