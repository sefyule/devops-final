package com.example.spring.demo.service;

import com.example.spring.demo.model.Person;
import com.example.spring.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Iterable<Person> getPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonByID(long id) {
        return personRepository.findById(id);
    }

    public boolean checkIfExist(long id) {
        return personRepository.findById(id).isPresent();
    }

    public boolean delete(long id) {
        personRepository.deleteById(id);
        return true;
    }

    public Person create(Person person) {
        person.setId(null);
        return personRepository.save(person);
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }
}
