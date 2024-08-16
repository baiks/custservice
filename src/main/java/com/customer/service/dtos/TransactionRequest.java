package com.customer.service.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TransactionRequest {
    private Long customerId;
    private String accountFrom;
    private String accountTo;
    private String narration;
    private Long amount;
}
