package com.customer.service.services;

import com.customer.service.dtos.AccountsDto;
import com.customer.service.entities.Accounts;
import org.springframework.http.ResponseEntity;


public interface AccountService {
    /**
     *
     * @param customerId
     * @return
     */
    ResponseEntity<Accounts> create(Long customerId);


    /**
     * @param id
     * @return
     */
    ResponseEntity<Accounts> findById(Long id);

    /**
     *
     * @param id
     * @return
     */
    ResponseEntity<Accounts> close(Long id);
}