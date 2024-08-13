package com.customer.service.services;

import com.customer.service.dtos.EditDto;
import com.customer.service.dtos.JwtResponse;
import com.customer.service.dtos.LoginDto;
import com.customer.service.dtos.SignupDto;
import org.springframework.http.ResponseEntity;


public interface AuthService {
    /**
     * @param signupDto
     * @return
     */
    ResponseEntity<SignupDto> signUp(SignupDto signupDto);

    /**
     * @param loginDto
     * @return
     */
    ResponseEntity<JwtResponse> authUser(LoginDto loginDto);

    /**
     *
     * @param editDto
     * @param id
     * @return
     */
    ResponseEntity<EditDto> editUser(EditDto editDto, Long id);
}