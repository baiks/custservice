package com.customer.service.services;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;


public interface HttpCallsService {
    HttpResponse post(String request, String url);

    HttpResponse patch(String request, String url);

    HttpResponse get(String url);
}
