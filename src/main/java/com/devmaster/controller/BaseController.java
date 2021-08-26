package com.devmaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devmaster.entity.Person;
import com.devmaster.model.PersonDTO;
import com.devmaster.service.PersonService;

@Controller
public class BaseController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/")
	public String index(Model model, @RequestParam(name = "id", required = false) Long id) {
		model.addAttribute("title", "Demo JPA-Hibernate");
		List<Person> persons = personService.getAllPerson(id);
		model.addAttribute("persons", persons);
		return "person";
	}
	
	@GetMapping("/test-api")
	public String testApiPage() {
		return "person-api";
	}
	
	@ResponseBody
	@GetMapping("/custom")
	public List<PersonDTO> getPersonInfo() {
		return personService.getCustomInfoPerson();
	}
	
	@PostMapping("/create-person")
	public String createPerson(@RequestParam(name = "id") Long id,
			@RequestParam("name") String name,
			@RequestParam("gender") String gender,
			@RequestParam("age") Integer age, @RequestParam("address") String address) {
		
		this.personService.createPerson(id, name, gender, age, address);
		return "redirect:/";
	}
	
	@GetMapping("/edit-person/{id}")
	public String editPersonPage(@PathVariable(name = "id") Long id, Model model) {
		Person person = this.personService.getPersonById(id);
		model.addAttribute("person", person);
		return "update-person";
	}
	
	@PostMapping("/update-person")
	public String updatePerson(@RequestParam(name = "id") Long id,
			@RequestParam("name") String name,
			@RequestParam("gender") String gender,
			@RequestParam("age") Integer age, @RequestParam("address") String address) {
		
		this.personService.updatePerson(id, name, gender, age, address);
		return "redirect:/";
	}
	
	@GetMapping("/delete-person/{id}")
	public String deletePerson(@PathVariable(name = "id") Long id) {
		this.personService.deletePerson(id);
		return "redirect:/";
	}
}
