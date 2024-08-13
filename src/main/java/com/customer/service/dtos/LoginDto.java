package com.customer.service.dtos;


import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class LoginDto {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank(message = "Please enter a password")
    @Size(min = 6, max = 40, message = "Password should be between 6 and 40")
    private String password;

}