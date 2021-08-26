package com.devmaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmaster.entity.Person;
import com.devmaster.model.CreatePersonRequest;
import com.devmaster.service.PersonService;

@RestController
@RequestMapping("/api")
public class DevmasterController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/all-person")
	public List<Person> getPersons() {
		return this.personService.getAllPerson(null);
	}
	
	@GetMapping("/get-person-by-id")
	public Person getPersonById(Long id) {
		return this.personService.getPersonById(id);
	}
	
	@PostMapping("/create-person")
	public void createPerson (@RequestBody CreatePersonRequest request) {
		personService.createPerson(request.getId(), request.getName(), request.getGender(), request.getAge(), request.getAddress());
	}
	
	@PutMapping("/update-person")
	public void updatePerson(@RequestBody CreatePersonRequest request) {
		personService.updatePerson(request.getId(), request.getName(), request.getGender(), request.getAge(), request.getAddress());
	}
	
	@DeleteMapping("/delete-person/{id}")
	public void deletePerson(@PathVariable("id") Long id) {
		personService.deletePerson(id);
	}
}
