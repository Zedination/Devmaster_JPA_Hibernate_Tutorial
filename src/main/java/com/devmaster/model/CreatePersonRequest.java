package com.devmaster.model;

import lombok.Data;

@Data
public class CreatePersonRequest {
	
	private Long id;

	private String name;
	
	private String gender;
	
	private Integer age;
	
	private String address;
	
}
