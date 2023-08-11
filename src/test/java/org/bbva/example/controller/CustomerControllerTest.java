package org.bbva.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bbva.example.model.*;
import org.bbva.example.services.CustomerService;
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

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    Person person = Person.builder().name("Cruz Isaac").lastName("Aranda Cervantes").age(23).build();
    Email email = Email.builder().email("cruz@hotmail.com").password("1234567").build();
    Sales sales = Sales.builder().description("Sold clothes").build();

    @Test
    public void addCustomer() throws Exception {

        Customer customer = Customer.builder()
                .address("Faro del Carme")
                .id_person(person)
                .id_email(email)
                .build();

        ResultActions response = mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)));

        response.andExpect(status().isOk());
        response.andDo(print());
    }

    @Test
    public void getAllCustomer() throws Exception {

        List<Customer> customerList = new ArrayList<>();

        customerList.add(Customer.builder()
                .address("Faro del Carme")
                .id_person(person)
                .id_email(email)
                .build());

        given(customerService.getAllCustomer()).willReturn((ArrayList<Customer>) customerList);

        ResultActions response = mockMvc.perform(get("/customer"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(customerList.size())));

    }

    @Test
    public void getCustomerBy() throws Exception {
        long customerId = 1L;
        Customer customer = Customer.builder()
                .address("Faro del Carme")
                .id_person(person)
                .id_email(email)
                .build();
        given(customerService.getIdCustomer(customerId)).willReturn(customer);

        ResultActions response = mockMvc.perform(get("/customer/{id}", customerId));

        response.andExpect(status().isOk());
        response.andDo(print());
        response.andExpect(jsonPath("$.address", is(customer.getAddress())));
    }

    @Test
    public void updateCustomer() throws Exception {
        long customerId = 1L;
        Customer addCustomer = Customer.builder()
                .address("Faro del Carme")
                .id_person(person)
                .id_email(email)
                .build();

        Customer updateCustomer = Customer.builder()
                .address("San Juan Bosco")
                .id_person(person)
                .id_email(email)
                .build();

        given(customerService.getIdCustomer(customerId)).willReturn(addCustomer);
        given(customerService.updateCustomer(any(Customer.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform((put("/customer/{id}", customerId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateCustomer)));

        response.andExpect(status().isOk());
        response.andDo(print());
    }

    @Test
    public void deleteCustomer() throws Exception {
        long customerId = 1L;
        Customer customer = Customer.builder()
                .address("Faro del Carme")
                .id_person(person)
                .id_email(email)
                .build();

        given(customerService.getIdCustomer(customerId)).willReturn(customer);

        ResultActions response = mockMvc.perform(delete("/customer/{id}", customerId));

        response.andExpect(status().isOk());
        response.andDo(print());
    }

}
