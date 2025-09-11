package com.akashi.TaskManager.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

// Lombok - GetAndSetter for all private Strings in this file
@Data
@Entity
@Table(name = "tb_users")
public class UserModel {

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;

	@Column(unique = true)
	private String username;
	private String name;
	private String password;

	@CreationTimestamp
	private LocalDateTime createdAt;
}
