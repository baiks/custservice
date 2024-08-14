package com.customer.service;

import com.customer.service.dtos.EditDto;
import com.customer.service.dtos.LoginDto;
import com.customer.service.dtos.SignupDto;
import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {
    @Autowired
    private MockMvc mockMvc;
    private final String username = RandomStringUtils.randomAlphabetic(10);
    private final String password = RandomStringUtils.randomAlphanumeric(15);

    @Test
    @DisplayName("testSignUpSuccess")
    public void testSignUpSuccess() throws Exception {
        SignupDto signupDto = SignupDto.builder()
                .username(username)
                .password(password)
                .address("1234")
                .email(RandomStringUtils.randomAlphabetic(15) + "@gmail.com")
                .country("KE")
                .dateOfBirth(new Date())
                .firstName(RandomStringUtils.randomAlphabetic(4))
                .middleName(RandomStringUtils.randomAlphabetic(10))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .identificationNumber(RandomStringUtils.randomAlphanumeric(10))
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/auth/signup").content(new Gson().toJson(signupDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
    }

    @Test
    @DisplayName("testSignUpFailed")
    public void testSignUpFailed() throws Exception {
        SignupDto signupDto = SignupDto.builder()
                .username(username)
                .password(RandomStringUtils.randomAlphanumeric(15))
                .address("1234")
                .email(RandomStringUtils.randomAlphabetic(15) + "@gmail.com")
                .country("KE")
                .dateOfBirth(new Date())
                .firstName(RandomStringUtils.randomAlphabetic(4))
                .middleName(RandomStringUtils.randomAlphabetic(10))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .identificationNumber(RandomStringUtils.randomAlphanumeric(10))
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/auth/signup").content(new Gson().toJson(signupDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("testLoginSuccess")
    public void testLoginSuccess() throws Exception {
        LoginDto loginDto = LoginDto.builder()
                .username(username)
                .password(password)
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/auth/login").content(new Gson().toJson(loginDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().is2xxSuccessful()).andReturn();
    }

    @Test
    @DisplayName("testLoginFailed")
    public void testLoginFailed() throws Exception {
        LoginDto loginDto = LoginDto.builder()
                .username(username)
                .password("password")
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/auth/login").content(new Gson().toJson(loginDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("testEditSuccess")
    public void testEditSuccess() throws Exception {
        EditDto editDto = EditDto.builder()
                .firstName(RandomStringUtils.randomAlphabetic(4))
                .middleName(RandomStringUtils.randomAlphabetic(10))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .patch("/auth/edit/{id}", -1).content(new Gson().toJson(editDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().is2xxSuccessful()).andReturn();
    }

    @Test
    @DisplayName("testEditFailed")
    public void testEditFailed() throws Exception {
        EditDto editDto = EditDto.builder()
                .firstName(RandomStringUtils.randomAlphabetic(4))
                .middleName(RandomStringUtils.randomAlphabetic(10))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .patch("/auth/edit/{id}", -1).content(new Gson().toJson(editDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
    }

}
