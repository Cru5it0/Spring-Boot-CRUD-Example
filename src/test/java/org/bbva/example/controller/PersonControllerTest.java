package org.bbva.example.controller;

import org.bbva.example.model.Person;
import org.bbva.example.reposiroty.PersonRepository;
import org.bbva.example.services.PersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PersonServices personServices;

    @Test
    public void getAllPeople() throws Exception {
        List<Person> peopleList = new ArrayList<>();
        peopleList.add(Person.builder().name("Cruz Isaac").lastName("Aranda Cervantes").age(23).build());
        peopleList.add(Person.builder().name("Danil").lastName("Uribe").age(23).build());
        given(personServices.getAllPersons()).willReturn((ArrayList<Person>) peopleList);

        ResultActions response = mockMvc.perform(get("/person"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect((ResultMatcher) jsonPath("$.size()", is(peopleList.size())));
    }

}
