package com.customer.service.controllers;


import com.customer.service.dtos.EditDto;
import com.customer.service.dtos.JwtResponse;
import com.customer.service.dtos.LoginDto;
import com.customer.service.dtos.SignupDto;
import com.customer.service.entities.Customers;
import com.customer.service.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "AUTH", description = "Manages customer data, including registration, authentication, and profile management.")
public class AuthController {


    private final AuthService authService;

    @RequestMapping(method = RequestMethod.GET, value = "/ping")
    public String ping() {
        return "Ping successful!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    @Operation(summary = "Create a user", description = "Returns the details of a user.\n" + "\n" + "Example Requests:\n" + "\n" + "{\n" +
            "  \"username\": \"2M015D_FghDV1Jsv4lQR\",\n" +
            "  \"password\": \"string\",\n" +
            "  \"firstName\": \"string\",\n" +
            "  \"middleName\": \"string\",\n" +
            "  \"lastName\": \"string\",\n" +
            "  \"nationalId\": \"stringst\",\n" +
            "  \"address\": \"string\",\n" +
            "  \"mobileNumber\": \"stringstr\",\n" +
            "  \"dateOfBirth\": \"2024-08-13T18:50:44.160Z\",\n" +
            "  \"country\": \"stringstr\",\n" +
            "  \"email\": \"string\"\n" +
            "}")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /signup")})
    public ResponseEntity<Customers> register(@Valid @RequestBody SignupDto signupDto) {
        return authService.signUp(signupDto);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @Operation(summary = "Login user", description = "Returns the JWT.\n" + "\n" + "Example Requests:\n" + "\n" + "jwt")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /login")})
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto loginDto) {
        return authService.authUser(loginDto);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/edit/{id}")
    @Operation(summary = "Edit user", description = "Returns the details of updated user.\n" + "\n" + "Example Requests:\n" + "\n" + "{\n" +
            "  \"username\": \"string\",\n" +
            "  \"password\": \"string\",\n" +
            "  \"firstName\": \"string\",\n" +
            "  \"middleName\": \"string\",\n" +
            "  \"lastName\": \"string\",\n" +
            "  \"nationalId\": \"string\",\n" +
            "  \"address\": \"string\",\n" +
            "  \"mobileNumber\": \"string\",\n" +
            "  \"dateOfBirth\": \"2024-08-13T19:03:51.585Z\",\n" +
            "  \"countryCode\": \"string\",\n" +
            "  \"email\": \"string\"\n" +
            "}")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "PATCH: /edit/1")})
    public ResponseEntity<Customers> edit(@Valid @RequestBody EditDto editDto, @PathVariable Long id) {
        return authService.editUser(editDto, id);
    }
}