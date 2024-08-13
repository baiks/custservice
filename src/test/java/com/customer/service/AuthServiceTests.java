package com.customer.service;

import com.customer.service.dtos.EditDto;
import com.customer.service.dtos.LoginDto;
import com.customer.service.dtos.SignupDto;
import com.customer.service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;

import java.util.Date;

@SpringBootTest
public class AuthServiceTests {
    private final String username = RandomStringUtils.randomAlphabetic(10);
    private final String password = RandomStringUtils.randomAlphanumeric(15);
    @Autowired
    AuthService authService;

    @DisplayName("testsignUpSuccess")
    @Test
    public void testsignUpSuccess() {
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
                .nationalId(RandomStringUtils.randomAlphanumeric(10))
                .build();
        Assertions.assertEquals(authService.signUp(signupDto).getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @DisplayName("testsignUpFailed")
    public void testsignUpFailed() {
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
                .nationalId(RandomStringUtils.randomAlphanumeric(10))
                .build();
        Assertions.assertEquals(authService.signUp(signupDto).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("testsignUpSuccess")
    public void testLoginSuccess() {
        LoginDto loginDto = LoginDto.builder()
                .username(username)
                .password(password)
                .build();
        Assertions.assertEquals(authService.authUser(loginDto).getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("testLoginFailed")
    public void testLoginFailed() {
        LoginDto loginDto = LoginDto.builder()
                .username(username)
                .password(password)
                .build();
        Assertions.assertEquals(authService.authUser(loginDto).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("testsignUpSuccess")
    public void testEditSuccess() {
        EditDto editDto = EditDto.builder()
                .firstName(RandomStringUtils.randomAlphabetic(4))
                .middleName(RandomStringUtils.randomAlphabetic(10))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .nationalId(RandomStringUtils.randomAlphanumeric(10))
                .build();
        Assertions.assertEquals(authService.editUser(editDto, 1L).getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("testsignUpFailed")
    public void testEditFailed() {
        EditDto editDto = EditDto.builder()
                .firstName(RandomStringUtils.randomAlphabetic(4))
                .middleName(RandomStringUtils.randomAlphabetic(10))
                .lastName(RandomStringUtils.randomAlphabetic(10))
                .nationalId(RandomStringUtils.randomAlphanumeric(10))
                .build();
        Assertions.assertEquals(authService.editUser(editDto, -3L).getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
