package com.customer.service.dtos;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Getter
@Setter
@Builder
public class EditDto {
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalId;
    private String address;
    private String mobileNumber;
    private Date dateOfBirth;
    private String countryCode;
    private String email;

}