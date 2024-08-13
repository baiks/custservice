package com.customer.service.dtos;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class SignupDto {

    @Pattern(regexp = "\\w+", message = "Username should not contain any special character")
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank(message = "Please enter a password")
    @Size(min = 6, max = 40, message = "Minimum of 6 characters expected")
    private String password;
    @NotBlank(message = "Please enter a valid firstName")
    @Size(min = 1, max = 100, message = "Minimum of 6 and a maximum of 100 characters expected")
    private String firstName;
    @NotBlank(message = "Please enter a valid middleName")
    @Size(min = 1, max = 100, message = "Error: middleName. Minimum of 6 and a maximum of 100 characters expected")
    private String middleName;
    @NotBlank(message = "Please enter a valid lastName")
    @Size(min = 1, max = 100, message = "Error: lastName.Minimum of 6 and a maximum of 100 characters expected")
    private String lastName;
    @NotBlank(message = "Please enter a valid nationalId")
    @Size(min = 8, max = 10, message = "Error: nationalId.Minimum of 8 and a maximum of 10 characters expected")
    private String nationalId;
    @NotBlank(message = "Please enter a valid address")
    @Size(min = 1, max = 50, message = "Error: address. Minimum of 1 and a maximum of 50 characters expected")
    private String address;
    @NotBlank(message = "Please enter a valid mobileNumber")
    @Size(min = 9, max = 12, message = "Error: mobileNumber. Minimum of 1 and a maximum of 50 characters expected")
    private String mobileNumber;
    private Date dateOfBirth;
    @NotBlank(message = "Please enter a valid mobileNumber")
    @Size(min = 9, max = 12, message = "Error: mobileNumber. Minimum of 1 and a maximum of 50 characters expected")
    private String country;
    @NotBlank(message = "Please enter a valid email")
    @Size(min = 1, max = 50, message = "Error: email. Minimum of 1 and a maximum of 50 characters expected")
    private String email;

}