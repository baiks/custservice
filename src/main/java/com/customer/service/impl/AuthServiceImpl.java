package com.customer.service.impl;


import com.customer.service.dtos.EditDto;
import com.customer.service.dtos.JwtResponse;
import com.customer.service.dtos.LoginDto;
import com.customer.service.dtos.SignupDto;
import com.customer.service.entities.Users;
import com.customer.service.exception.CustomException;
import com.customer.service.jwt.JwtUtils;
import com.customer.service.repos.UsersRepository;
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
    private final UsersRepository usersRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;

    /**
     * @param signupDto
     * @return
     */
    @Override
    public ResponseEntity<SignupDto> signUp(SignupDto signupDto) {
        Optional<Users> res = usersRepository.findByUsername(signupDto.getUsername().toLowerCase());
        if (res.isPresent()) {
            throw new CustomException("A user with the given username already exists.Try a different one.");
        }
        Users users = modelMapper.map(signupDto, Users.class);
        users.setUsername(users.getUsername());
        users.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        usersRepository.save(users);
        return new ResponseEntity<>(modelMapper.map(signupDto, SignupDto.class), HttpStatus.CREATED);
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
            log.error("Bad credentials " + e.getMessage());
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
        Optional<Users> res = usersRepository.findById(id);
        if (!res.isPresent()) {
            throw new CustomException("A user with the given id doesn't exist");
        }
        Users users = modelMapper.map(editDto, Users.class);
        usersRepository.save(users);
        return new ResponseEntity<>(modelMapper.map(editDto, EditDto.class), HttpStatus.OK);
    }

}