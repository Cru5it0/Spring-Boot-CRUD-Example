package org.bbva.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bbva.example.model.Email;
import org.bbva.example.model.Employee;
import org.bbva.example.model.Person;
import org.bbva.example.model.Sales;
import org.bbva.example.services.EmployeeService;
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

    Person person = Person.builder().name("Cruz Isaac").lastName("Aranda Cervantes").age(23).build();
    Email email = Email.builder().email("cruz@hotmail.com").password("1234567").build();
    Sales sales = Sales.builder().description("Sold clothes").build();

    @Test
    public void addEmployee() throws Exception {

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

        response.andExpect(status().isOk());
        response.andDo(print());

   }

   @Test
    public void getAllEmployee() throws Exception {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(Employee.builder().Job("Staff").status(1)
                .id_person(person)
                .id_email(email)
                .id_sales(sales)
                .build());

        given(employeeService.getAllEmployee()).willReturn((ArrayList<Employee>) employeeList);

        ResultActions response = mockMvc.perform(get("/employee"));

       response.andExpect(status().isOk())
               .andDo(print())
               .andExpect(jsonPath("$.size()", is(employeeList.size())));
   }

   @Test
    public void getEmployeeId() throws Exception {
        long employeeId = 1L;
        Employee employee = Employee.builder().Job("Staff").status(1)
                .id_person(person)
                .id_email(email)
                .id_sales(sales)
                .build();
        given(employeeService.getIdEmployee(employeeId)).willReturn(employee);

        ResultActions response = mockMvc.perform(get("/employee/{id}", employeeId));

       response.andExpect(status().isOk());
       response.andDo(print());
       response.andExpect(jsonPath("$.job", is(employee.getJob())));
       response.andExpect(jsonPath("$.status", is(employee.getStatus())));
   }

   @Test
    public void updateEmployee() throws Exception {
        long employeeId = 1L;
       Employee addEmployee = Employee.builder().Job("Staff").status(1)
               .id_person(person)
               .id_email(email)
               .id_sales(sales)
               .build();

       Employee updateEmployee = Employee.builder().Job("Human Resources").status(0)
               .id_person(person)
               .id_email(email)
               .id_sales(sales)
               .build();

       given(employeeService.getIdEmployee(employeeId)).willReturn(addEmployee);
       given(employeeService.updateEmployee(any(Employee.class)))
               .willAnswer((invocation) -> invocation.getArgument(0));

       ResultActions response = mockMvc.perform(put("/employee/{id}", employeeId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(updateEmployee)));

       response.andExpect(status().isOk());
       response.andDo(print());

   }

   @Test
    public void deleteEmployee() throws Exception {
        long employeeId = 1L;
       Employee employee = Employee.builder().Job("Staff").status(1)
               .id_person(person)
               .id_email(email)
               .id_sales(sales)
               .build();

       given(employeeService.getIdEmployee(employeeId)).willReturn(employee);

       ResultActions response = mockMvc.perform(delete("/employee/{id}", employeeId));

       response.andExpect(status().isOk());
       response.andDo(print());
   }

}
