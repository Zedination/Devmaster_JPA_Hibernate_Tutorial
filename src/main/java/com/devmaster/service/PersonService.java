package com.devmaster.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.devmaster.model.CreatePersonRequest;
import com.devmaster.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmaster.entity.Person;
import com.devmaster.model.PersonDTO;

@Service
@RequiredArgsConstructor
public class PersonService {

	private final EntityManager entityManager;

	private final PersonRepository personRepository;
	
	public List<Person> getAllPerson(Long id) {
		if(id == null) {
			TypedQuery<Person> query = entityManager.createQuery("select p from Person p", Person.class);
			return query.getResultList();
//			Query query = entityManager.createNativeQuery("select * from person", Person.class);
//			return query.getResultList();
		}
		TypedQuery<Person> query = entityManager.createQuery("select p from Person p where p.id = :id", Person.class);
		query.setParameter("id", id);
//		Query query = entityManager.createNativeQuery("select * from person where id = :id");
//		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<Person> getPersonByPage(String keyword, Integer pageNumber, Integer pageSize) {
		TypedQuery<Person> query = entityManager.createQuery("select p from Person p where p.nameRemovedAccent like :keyword", Person.class);
		query.setParameter("keyword", keyword);
		int position = pageNumber * pageSize;
		query.setFirstResult(position);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	public int getNumberOfPage(int pageSize, String keyword) {
		TypedQuery<Long> countPersonQuery = entityManager.createQuery("select count(p.id) from Person p where p.nameRemovedAccent like :keyword", Long.class);
		countPersonQuery.setParameter("keyword", keyword);
		Long countPerson = countPersonQuery.getSingleResult();
		return (int) Math.ceil(Double.valueOf(countPerson) / Double.valueOf(pageSize));
	}
	
	public List<PersonDTO> getCustomInfoPerson() {
		TypedQuery<PersonDTO> query = entityManager.createQuery("select new com.devmaster.model.PersonDTO(p.name, p.gender, p.address) from Person p", PersonDTO.class);
		return query.getResultList();
	}
	
	@Transactional
	public void createPerson(Long id, String name, String gender, Integer age, String address) {
		Session session = entityManager.unwrap(Session.class);
		Person person = new Person();
		person.setName(name);
		person.setGender(gender);
		person.setAge(age);
		person.setAddress(address);
		session.save(person);
	}
	
	public Person getPersonById(Long id) {
		return entityManager.find(Person.class, id);
	}
	
	@Transactional
	public void updatePerson(Long id, String name, String gender, Integer age, String address) {
		Session session = entityManager.unwrap(Session.class);
		Person person = session.load(Person.class, id);
		person.setName(name);
		person.setGender(gender);
		person.setAge(age);
		person.setAddress(address);
	}
	
	@Transactional
	public void deletePerson(Long id) {
		Person person = entityManager.find(Person.class, id);
		entityManager.remove(person);
	}
	
	private List<Person> getResult(List<Object[]> inputs) {
		List<Person> results = new ArrayList<>();
		inputs.forEach(input -> {
			Person person = new Person();
			person.setId(((BigInteger)input[0]).longValue());
			person.setName((String)input[1]);
			person.setAge((Integer)input[3]);
			person.setGender((String)input[2]);
			person.setAddress((String)input[4]);
			results.add(person);
		});
		return results;
	}


	// ==== CRUD với Spring data JPA ====

	public Iterable<Person> getAllPerson(){
		return this.personRepository.findAll();
	}

	/**
	 * Method này vừa là create person, vừa là update person
	 * Nếu createPersonRequest.getId() == null thì là create person
	 *
	 * @param createPersonRequest
	 */
	public void modifyPerson(CreatePersonRequest createPersonRequest) {
		Person person = createPersonRequest.getId() == null ? new Person() : personRepository.findById(createPersonRequest.getId()).get();
//		Person person = personRepository.findById(createPersonRequest.getId()).orElseGet(Person::new);
		person.setName(createPersonRequest.getName());
		person.setAge(createPersonRequest.getAge());
		person.setGender(createPersonRequest.getGender());
		person.setAddress(createPersonRequest.getAddress());
		personRepository.save(person);
	}

	public void deletePersonBySpringData(Long id) {
		Optional<Person> person = personRepository.findById(id);
		person.ifPresent(personRepository::delete);
	}
}
