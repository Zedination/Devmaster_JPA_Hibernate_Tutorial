package com.devmaster.controller;

import com.devmaster.entity.Person;
import com.devmaster.model.CreatePersonRequest;
import com.devmaster.repository.PersonRepository;
import com.devmaster.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spring-data")
@AllArgsConstructor
public class SpringDataExampleController {

    private PersonService personService;

    @GetMapping("/all-person")
    public Iterable<Person> findAllPerson() {
        return personService.getAllPerson();
    }

    @PostMapping("/modify-person")
    public void modifyPerson(@RequestBody CreatePersonRequest createPersonRequest){
        this.personService.modifyPerson(createPersonRequest);
    }

    @DeleteMapping("/delete-person/{id}")
    public void deletePerson(@PathVariable(name = "id", required = true) Long id){
        this.personService.deletePersonBySpringData(id);
    }
}
