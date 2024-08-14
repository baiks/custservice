package com.customer.service.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class NotificationDto {
    private String recipient;
    private String message;
    private int type;
}
