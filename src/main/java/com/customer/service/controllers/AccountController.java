package com.customer.service.controllers;


import com.customer.service.dtos.AccountsDto;
import com.customer.service.entities.Accounts;
import com.customer.service.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "ACCOUNT", description = "Handles the management of bank accounts, including account\n" +
        "creation, balance inquiries, and account closure.")
public class AccountController {


    private final AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/ping")
    public String ping() {
        return "Ping successful!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create/{customerId}")
    @Operation(summary = "Create an account", description = "Returns the details of the account created.\n" + "\n" + "Example Requests:\n" + "\n" + "{\n" +
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
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /create/{customerId}")})
    public ResponseEntity<Accounts> register(@Valid @RequestBody AccountsDto accountsDto, @PathVariable Long customerId) {
        return accountService.create(accountsDto, customerId);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/view/{id}")
    @Operation(summary = "Login user", description = "Returns the account details.\n" + "\n" + "Example Requests:\n" + "\n" + "jwt")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "GET: /view/{id}")})
    public ResponseEntity<Accounts> findById(@Valid @PathVariable Long customerId) {
        return accountService.findById(customerId);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/close/{id}")
    @Operation(summary = "Close account", description = "Returns the details of the  closed account.\n" + "\n" + "Example Requests:\n" + "\n" + "{\n" +
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
    @ApiResponses({@ApiResponse(responseCode = "200", description = "PATCH: /close/1")})
    public ResponseEntity<Accounts> close(@Valid @PathVariable Long id) {
        return accountService.close(id);
    }
}