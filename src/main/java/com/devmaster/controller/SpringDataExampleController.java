package com.devmaster.controller;

import com.devmaster.entity.Person;
import com.devmaster.model.CreatePersonRequest;
import com.devmaster.repository.PersonRepository;
import com.devmaster.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/spring-data")
@AllArgsConstructor
public class SpringDataExampleController {

    private PersonService personService;

    private PersonRepository personRepository;

    @GetMapping("/all-person")
    public Iterable<Person> findAllPerson() {
        return this.personRepository.findAll();
    }

    @PostMapping("/modify-person")
    public void modifyPerson(@RequestBody CreatePersonRequest createPersonRequest){
        this.personService.modifyPerson(createPersonRequest);
    }

    @DeleteMapping("/delete-person/{id}")
    public void deletePerson(@PathVariable(name = "id", required = true) Long id){
        this.personService.deletePersonBySpringData(id);
    }

    @GetMapping("/find-custom")
    public String findPersonByName(Long id){
        return this.personRepository.timKiemTheoId(id).orElseGet(Person::new).getName();
    }

    @GetMapping("/get-all-person-native")
    public List<Person> getAllPersonByNativeSQL(){
        return this.personRepository.getAllPerson();
    }

//    @Transactional
    @PutMapping("/update")
    public void updateAll() {
        this.personRepository.updateCreateAtAllRecord(LocalDateTime.now());
    }

    @GetMapping("/sort")
    public Iterable<Person> findAllSort() {
//        Sort sort = Sort.by("name").and(Sort.by("age").descending());
        Sort.TypedSort<Person> personTypedSort = Sort.sort(Person.class);
        Sort typedSort = personTypedSort.by(Person::getName).descending()
                .and(personTypedSort.by(Person::getAge).ascending());
        return this.personRepository.findAll(typedSort);
    }

    @GetMapping("/pagination")
    public Page<Person> pagingPerson(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.personRepository.findAll(pageable);
    }

}
