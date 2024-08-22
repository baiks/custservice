package com.customer.service.impl;


import com.customer.service.entities.Accounts;
import com.customer.service.services.AccountService;
import com.customer.service.services.HttpCallsService;
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
public class AccountServiceImpl implements AccountService {
    private final HttpCallsService httpCallsService;
    @Value("${micro.services.accounts.url}")
    public String url;


    /**
     * @param customerId
     * @return
     */
    @Override
    public ResponseEntity<Accounts> create(Long customerId) {
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpCallsService.post(null, url + "create/" + customerId);
            HttpEntity entity = httpResponse.getEntity();
            Accounts accounts = new Gson().fromJson(EntityUtils.toString(entity), Accounts.class);
            return new ResponseEntity<>(accounts, HttpStatusCode.valueOf(httpResponse.getStatusLine().getStatusCode()));
        } catch (IOException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Accounts> findById(Long id) {
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpCallsService.get(url + "view/" + id);
            HttpEntity entity = httpResponse.getEntity();
            Accounts accounts = new Gson().fromJson(EntityUtils.toString(entity), Accounts.class);
            return new ResponseEntity<>(accounts, HttpStatusCode.valueOf(httpResponse.getStatusLine().getStatusCode()));
        } catch (IOException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Accounts> close(Long id) {
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpCallsService.patch("", url + "close/" + id);
            HttpEntity entity = httpResponse.getEntity();
            Accounts accounts = new Gson().fromJson(EntityUtils.toString(entity), Accounts.class);
            return new ResponseEntity<>(accounts, HttpStatusCode.valueOf(httpResponse.getStatusLine().getStatusCode()));
        } catch (IOException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Accounts> activate(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Accounts> deActivate(Long id) {
        return null;
    }
}