package com.devmaster;

import com.devmaster.repository.PersonRepository;
import com.devmaster.service.CommonService;
import com.devmaster.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class DevmasterJpaHibernateTutorialApplication implements CommandLineRunner {

	private final PersonService personService;

	private final CommonService commonService;

	private final PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(DevmasterJpaHibernateTutorialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		personService.getAllPerson(null).forEach(person -> {
//			String removedAccent = commonService.removeAccent(person.getName());
//			person.setNameRemovedAccent(removedAccent);
//			personRepository.save(person);
//		});

	}
}
