package org.bbva.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bbva.example.model.Sales;
import org.bbva.example.services.PersonServices;
import org.bbva.example.services.SalesService;
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

@WebMvcTest(SalesController.class)
public class SalesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesService salesService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void AddSales() throws Exception {

        Sales sales = Sales.builder()
                .description("Sold clothes")
                .build();

        given(salesService.save(any(Sales.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/sales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sales)));

        response.andDo(print());
        response.andExpect(jsonPath("$.description", is(sales.getDescription())));

    }

    @Test
    public void getAllSales() throws Exception {

        List<Sales> salesList = new ArrayList<>();
        salesList.add(Sales.builder().description("Sold clothes").build());
        salesList.add(Sales.builder().description("Sold clothes").build());
        salesList.add(Sales.builder().description("Recruit employees").build());

        given(salesService.getAllSales()).willReturn((ArrayList<Sales>) salesList);

        ResultActions response = mockMvc.perform(get("/sales"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(salesList.size())));

    }

    @Test
    public void getSalesId() throws Exception {

        long salesId = 1L;
        Sales sales = Sales.builder().description("Sold clothes").build();

        given(salesService.getIdSales(salesId)).willReturn(sales);

        ResultActions response = mockMvc.perform(get("/sales/{id}", salesId));

        response.andExpect(status().isOk());
        response.andDo(print());
        response.andExpect(jsonPath("$.description", is(sales.getDescription())));

    }

    @Test
    public void updateSales() throws Exception {

        long salesId = 1L;

        Sales addSales = Sales.builder().description("Sold clothes").build();
        Sales updateSales = Sales.builder().description("Recruit employees").build();

        given(salesService.getIdSales(salesId)).willReturn(addSales);
        given(salesService.updateSales(any(Sales.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/sales/{id}", salesId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateSales)));

        response.andExpect(status().isOk());
        response.andDo(print());

    }

    @Test
    public void deleteSales() throws Exception {

        long salesId = 1L;

        Sales sales = Sales.builder().description("Sold clothes").build();

        given(salesService.getIdSales(salesId)).willReturn(sales);

        ResultActions response = mockMvc.perform(delete("/sales/{id}", salesId));

        response.andExpect(status().isOk());
        response.andDo(print());

    }

}
