package com.devmaster.entity.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devmaster.entity.Person;

@Service
public class PersonService {

	@Autowired
	private EntityManager entityManager;
	
	public List<Person> getAllPerson() {
		TypedQuery<Person> query = entityManager.createQuery("select p from Person p", Person.class);
		return query.getResultList();
	}
	
	@Transactional
	public void createPerson(Long id, String name, String gender, Integer age, String address) {
		Session session = entityManager.unwrap(Session.class);
		Person person = new Person();
		person.setName(name);
		person.setId(id);
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
		person.setId(id);
		person.setGender(gender);
		person.setAge(age);
		person.setAddress(address);
	}
}
