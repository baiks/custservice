package com.customer.service.impl;

import com.customer.service.entities.Users;
import com.customer.service.exception.CustomException;
import com.customer.service.exception.ErrorResponse;
import com.customer.service.repos.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepo usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) {
        ErrorResponse errorResponse = null;
        Users user = usersRepository.findByUsername(name).orElseThrow(() -> new CustomException("Invalid Username"));
        return UserDetailsImpl.build(user);
    }

}