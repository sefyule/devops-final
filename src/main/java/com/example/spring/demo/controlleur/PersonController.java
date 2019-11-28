package com.example.spring.demo.controlleur;

import com.example.spring.demo.model.Person;
import com.example.spring.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    ResponseEntity<Iterable<Person>> getPersons() {
        return ResponseEntity.ok(personService.getPersons());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return personService.getPersonByID(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Person> postPerson(@RequestBody Person person){
        return ResponseEntity.ok(personService.create(person));
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Person> deletePerson(@PathVariable Long id) {
        return personService.getPersonByID(id)
                .map(person -> {
                    personService.delete(person.getId());
                    return person;
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

