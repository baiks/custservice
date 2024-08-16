package com.customer.service.services;


import com.customer.service.dtos.TransactionRequest;
import com.customer.service.dtos.TransactionResponse;
import org.springframework.http.ResponseEntity;


public interface TransactionsService {
    /**
     * @param transactionRequest
     * @param customerId
     * @return
     */
    ResponseEntity<TransactionResponse> deposit(TransactionRequest transactionRequest, Long customerId);

    /**
     *
     * @param transactionRequest
     * @param customerId
     * @return
     */
    ResponseEntity<TransactionResponse> withdrawal(TransactionRequest transactionRequest, Long customerId);

    /**
     *
     * @param transactionRequest
     * @param customerId
     * @return
     */
    ResponseEntity<TransactionResponse> transfer(TransactionRequest transactionRequest, Long customerId);
}