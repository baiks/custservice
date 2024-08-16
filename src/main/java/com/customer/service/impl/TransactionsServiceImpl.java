package com.customer.service.impl;


import com.customer.service.dtos.TransactionRequest;
import com.customer.service.dtos.TransactionResponse;
import com.customer.service.entities.Accounts;
import com.customer.service.services.HttpCallsService;
import com.customer.service.services.TransactionsService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {
    private final HttpCallsService httpCallsService;
    @Value("${micro.services.transactions.url}")
    public String url;

    /**
     * @param transactionRequest
     * @param customerId
     * @return
     */
    @Override
    public ResponseEntity<TransactionResponse> deposit(TransactionRequest transactionRequest, Long customerId) {
        HttpResponse httpResponse;
        try {
            httpResponse = httpCallsService.post(new Gson().toJson(transactionRequest), url + "deposit/" + customerId);
            HttpEntity entity = httpResponse.getEntity();
            TransactionResponse transactionResponse = new Gson().fromJson(EntityUtils.toString(entity), TransactionResponse.class);
            return new ResponseEntity<>(transactionResponse, HttpStatusCode.valueOf(httpResponse.getStatusLine().getStatusCode()));
        } catch (IOException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param transactionRequest
     * @param customerId
     * @return
     */
    @Override
    public ResponseEntity<TransactionResponse> withdrawal(TransactionRequest transactionRequest, Long customerId) {
        HttpResponse httpResponse;
        try {
            httpResponse = httpCallsService.post(new Gson().toJson(transactionRequest), url + "withdrawal/" + customerId);
            HttpEntity entity = httpResponse.getEntity();
            TransactionResponse transactionResponse = new Gson().fromJson(EntityUtils.toString(entity), TransactionResponse.class);
            return new ResponseEntity<>(transactionResponse, HttpStatusCode.valueOf(httpResponse.getStatusLine().getStatusCode()));
        } catch (IOException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param transactionRequest
     * @param customerId
     * @return
     */
    @Override
    public ResponseEntity<TransactionResponse> transfer(TransactionRequest transactionRequest, Long customerId) {
        HttpResponse httpResponse;
        try {
            httpResponse = httpCallsService.post(new Gson().toJson(transactionRequest), url + "transfer/" + customerId);
            HttpEntity entity = httpResponse.getEntity();
            TransactionResponse transactionResponse = new Gson().fromJson(EntityUtils.toString(entity), TransactionResponse.class);
            return new ResponseEntity<>(transactionResponse, HttpStatusCode.valueOf(httpResponse.getStatusLine().getStatusCode()));
        } catch (IOException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}