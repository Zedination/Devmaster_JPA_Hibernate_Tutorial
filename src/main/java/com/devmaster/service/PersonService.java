package com.devmaster.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmaster.entity.Person;
import com.devmaster.model.PersonDTO;

@Service
public class PersonService {

	@Autowired
	private EntityManager entityManager;
	
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

}
