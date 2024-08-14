package com.customer.service.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AccountsDto {
    private String accountNumber;
    private Long balance;
    private boolean active;
    private boolean closed;
}
