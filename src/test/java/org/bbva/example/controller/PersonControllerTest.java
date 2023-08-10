package org.bbva.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bbva.example.model.Person;
import org.bbva.example.services.PersonServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonServices personServices;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void AddPerson() throws Exception {
        Person person = Person.builder().name("Cruz Isaac").lastName("Aranda Cervantes").age(23).build();
        given(personServices.save(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)));

        response.andDo(print());
        response.andExpect(jsonPath("$.name", is(person.getName())));
        response.andExpect(jsonPath("$.lastName", is(person.getLastName())));
        response.andExpect(jsonPath("$.age", is(person.getAge())));
    }

    @Test
    public void getAllPeople() throws Exception {
        List<Person> peopleList = new ArrayList<>();
        peopleList.add(Person.builder().name("Cruz Isaac").lastName("Aranda Cervantes").age(23).build());
        peopleList.add(Person.builder().name("Danil").lastName("Uribe").age(23).build());

        given(personServices.getAllPersons()).willReturn((ArrayList<Person>) peopleList);

        ResultActions response = mockMvc.perform(get("/person"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(peopleList.size())));
    }

    @Test
    public void getPersonById() throws Exception {
        long personId = 1L;
        Person person = Person.builder()
                .name("Isaac")
                .lastName("Aranda")
                .age(23)
                .build();
        given(personServices.getIdPerson(personId)).willReturn(person);

        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        response.andExpect(status().isOk());
        response.andDo(print());
        response.andExpect(jsonPath("$.name", is(person.getName())));
        response.andExpect(jsonPath("$.lastName", is(person.getLastName())));
        response.andExpect(jsonPath("$.age", is(person.getAge())));

    }

    @Test
    public void updatePerson() throws Exception {

        long personId = 1L;

        Person addPerson = Person.builder()
                .name("Isaac")
                .lastName("Aranda")
                .age(23)
                .build();

        Person updatePerson = Person.builder()
                .name("Cruz")
                .lastName("Cervantes")
                .age(20)
                .build();

        given(personServices.getIdPerson(personId)).willReturn(addPerson);
        given(personServices.updatePerson(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/person/{id}", personId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatePerson)));

        response.andExpect(status().isOk());
        response.andDo(print());
        /*response.andExpect(jsonPath("$.name", is(updatePerson.getName())));
        response.andExpect(jsonPath("$.lastName", is(updatePerson.getLastName())));
        response.andExpect(jsonPath("$.age", is(updatePerson.getAge())));*/

    }

    @Test
    public void deletePerson() throws Exception {
        long personId = 1L;

        Person person = Person.builder()
                .name("Isaac")
                .lastName("Aranda")
                .age(23)
                .build();
        given(personServices.getIdPerson(personId)).willReturn(person);

        ResultActions response = mockMvc.perform(delete("/person/{id}", personId));

        response.andExpect(status().isOk());
        response.andDo(print());
    }

}