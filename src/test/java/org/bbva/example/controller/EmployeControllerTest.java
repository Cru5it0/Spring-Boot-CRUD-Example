package org.bbva.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bbva.example.model.Email;
import org.bbva.example.model.Employee;
import org.bbva.example.model.Person;
import org.bbva.example.model.Sales;
import org.bbva.example.services.EmployeeService;
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

@WebMvcTest(EmployeeController.class)
public class EmployeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void AddEmployee() throws Exception {

        Person person = Person.builder().name("Cruz Isaac").lastName("Aranda Cervantes").age(23).build();
        Email email = Email.builder().email("cruz@hotmail.com").password("1234567").build();
        Sales sales = Sales.builder().description("Sold clothes").build();

        Employee employee = Employee.builder()
                .id_person(person)
                .id_email(email)
                .id_sales(sales)
                .Job("Staff")
                .status(1)
                .build();

        ResultActions response = mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        response.andDo(print());


    }

}
