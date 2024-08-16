package com.customer.service.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class TransactionResponse {
    private String statusCode;
    private String statusMessage;
}
