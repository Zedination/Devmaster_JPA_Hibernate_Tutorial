package com.devmaster.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDTO {

	private String name;
	
	private String gender;
	
	private String address;
	
}
