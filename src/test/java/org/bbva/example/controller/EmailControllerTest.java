package org.bbva.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bbva.example.model.Email;
import org.bbva.example.services.EmailService;
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

@WebMvcTest(EmailController.class)
public class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void AddEmail() throws Exception {
        Email email = Email.builder()
                .email("cruz@hotmail.com")
                .password("1234567")
                .build();
        given(emailService.save(any(Email.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(email)));

        response.andDo(print());
        response.andExpect(jsonPath("$.email", is(email.getEmail())));
        response.andExpect(jsonPath("$.password", is(email.getPassword())));
    }

    @Test
    public void getAllEmail() throws Exception {
        List<Email> emailList = new ArrayList<>();
        emailList.add(Email.builder().email("cruz@hotmail.com").password("1234567").build());
        emailList.add(Email.builder().email("isaac@hotmail.com").password("1234567").build());
        emailList.add(Email.builder().email("paulina@gmail.com").password("1234567").build());

        given(emailService.getAllEmail()).willReturn((ArrayList<Email>) emailList);

        ResultActions response = mockMvc.perform(get("/email"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(emailList.size())));
    }

    @Test
    public void getEmailById() throws Exception {
        long emailId = 1L;

        Email email = Email.builder()
                .email("raul@hotmail.com")
                .password("1234567")
                .build();

        given(emailService.getIdEmail(emailId)).willReturn(email);

        ResultActions response =  mockMvc.perform(get("/email/{id}", emailId));

        response.andExpect(status().isOk());
        response.andDo(print());
        response.andExpect(jsonPath("$.email", is(email.getEmail())));
        response.andExpect(jsonPath("$.password", is(email.getPassword())));
    }

    @Test
    public void updateEmail() throws Exception {

        long emailId = 1L;

        Email addEmail = Email.builder()
                .email("raul@hotmail.com")
                .password("1234567")
                .build();

        Email updateEmail = Email.builder()
                .email("daniel@gmail.com")
                .password("7654321")
                .build();

        given(emailService.getIdEmail(emailId)).willReturn(addEmail);
        given(emailService.updateEmail(any(Email.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform((put("/email/{id}", emailId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateEmail)));

        response.andExpect(status().isOk());
        response.andDo(print());

    }

    @Test
    public void deleteEmail() throws Exception {

        long emailId = 1L;

        Email email = Email.builder()
                .email("oscar@hotmail.com")
                .password("1234567")
                .build();

        given(emailService.getIdEmail(emailId)).willReturn(email);

        ResultActions response = mockMvc.perform(delete("/email/{id}", emailId));

        response.andExpect(status().isOk());
        response.andDo(print());

    }

}
