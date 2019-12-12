package com.example.spring.demo;

import com.example.spring.demo.controlleur.PersonController;
import com.example.spring.demo.model.Person;
import com.example.spring.demo.repository.PersonRepository;
import com.example.spring.demo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;
    private PersonRepository personeRepository;

   /* @Test
    public void getOne() {
        Person p=  new Person(1L, "youcef", "atal");
        Mockito.when(personeRepository.findById(1L)).thenReturn(Optional.of(p));

    }

    */

    @Test
    public void getShouldResponseOk() throws Exception {
        when(personService.getPersons()).thenReturn(List.of(new Person(1L, "bosquet", "romain")));
        this.mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("romain"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void postShouldCreate() throws Exception {
        
    }



}
