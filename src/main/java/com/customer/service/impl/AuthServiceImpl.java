package com.customer.service.impl;


import com.customer.service.dtos.EditDto;
import com.customer.service.dtos.JwtResponse;
import com.customer.service.dtos.LoginDto;
import com.customer.service.dtos.SignupDto;
import com.customer.service.entities.Customers;
import com.customer.service.entities.Users;
import com.customer.service.exception.CustomException;
import com.customer.service.jwt.JwtUtils;
import com.customer.service.repos.CustomersRepo;
import com.customer.service.repos.UsersRepo;
import com.customer.service.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepo usersRepo;
    private final CustomersRepo customersRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;

    /**
     * @param signupDto
     * @return
     */
    @Override
    public ResponseEntity<Customers> signUp(SignupDto signupDto) {
        Optional<Users> res = usersRepo.findByUsername(signupDto.getUsername().toLowerCase());
        if (res.isPresent()) {
            throw new CustomException("A user with the given username already exists.Try a different one.");
        }

        //Capture customer
        Customers customers = modelMapper.map(signupDto, Customers.class);
        customersRepo.save(customers);

        //Capture customer user profile
        Users users = modelMapper.map(signupDto, Users.class);
        users.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        users.setCustomer(customers);
        usersRepo.save(users);

        return new ResponseEntity<>(customers, HttpStatus.CREATED);
    }

    /**
     * @param loginDto
     * @return
     */
    @Override
    public ResponseEntity<JwtResponse> authUser(LoginDto loginDto) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername().toLowerCase(Locale.ROOT).trim(),
                            loginDto.getPassword().trim()));


        } catch (BadCredentialsException e) {
            throw new CustomException("Invalid username or password.");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LocalDateTime presentDateTime = LocalDateTime.now();
        JwtResponse result = JwtResponse.builder()
                .username(userDetails.getUsername())
                .status_code(0)
                .role(null)
                .type("BEARER")
                .token(jwt)
                .issuedAt(presentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")))
                .expiry(presentDateTime.plusSeconds(jwtUtils.getTokenValidityInSecs()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")))
                .build();
        return ResponseEntity.ok(result);

    }

    @Override
    public ResponseEntity<EditDto> editUser(EditDto editDto, Long id) {
        Optional<Users> res = usersRepo.findById(id);
        if (!res.isPresent()) {
            throw new CustomException("A user with the given id doesn't exist");
        }
        Users users = modelMapper.map(editDto, Users.class);
        usersRepo.save(users);
        return new ResponseEntity<>(modelMapper.map(editDto, EditDto.class), HttpStatus.OK);
    }

}