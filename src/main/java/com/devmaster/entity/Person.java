package com.devmaster.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "address")
	private String address;

	@Column(name = "name_removed_accent")
	private String nameRemovedAccent;

	@CreationTimestamp
	@Column(name = "create_at")
	private LocalDateTime createAt;

	@UpdateTimestamp
	@Column(name = "update_at")
	private LocalDateTime updateAt;
	
}
