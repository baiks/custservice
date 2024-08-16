package com.customer.service.controllers;

import com.customer.service.dtos.TransactionRequest;
import com.customer.service.dtos.TransactionResponse;
import com.customer.service.services.TransactionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
@Tag(name = "TRANSACTIONS", description = "Manages financial transactions, such as deposits, withdrawals,and transfers between accounts.")
public class TransactionController {


    private final TransactionsService transactionsService;

    @RequestMapping(method = RequestMethod.POST, value = "/deposit/{customerId}")
    @Operation(summary = "Deposit transaction", description = "Returns the transaction status.\n" + "\n" + "Example Requests:\n" + "{\n" +
            "  \"customerId\": 0,\n" +
            "  \"accountFrom\": \"0000000001\",\n" +
            "  \"narration\": \"string\",\n" +
            "  \"amount\": 10\n" +
            "}")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /withdrawal/{customerId}")})
    public ResponseEntity<TransactionResponse> deposit(@Valid @RequestBody TransactionRequest transactionRequest, @PathVariable Long customerId) {
        return transactionsService.deposit(transactionRequest, customerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/withdrawal/{customerId}")
    @Operation(summary = "Deposit transaction", description = "Returns the transaction status.\n" + "\n" + "Example Requests:\n" + "{\n" +
            "  \"customerId\": 0,\n" +
            "  \"accountFrom\": \"0000000001\",\n" +
            "  \"accountTo\": \"string\",\n" +
            "  \"narration\": \"string\",\n" +
            "  \"amount\": 10\n" +
            "}")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /withdrawal/{customerId}")})
    public ResponseEntity<TransactionResponse> withdrawal(@Valid @RequestBody TransactionRequest transactionRequest, @PathVariable Long customerId) {
        return transactionsService.withdrawal(transactionRequest, customerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/transfer/{customerId}")
    @Operation(summary = "Deposit transaction", description = "Returns the transaction status.\n" + "\n" + "Example Requests:\n" + "{\n" +
            "  \"customerId\": 0,\n" +
            "  \"accountFrom\": \"0000000001\",\n" +
            "  \"accountTo\": \"string\",\n" +
            "  \"narration\": \"string\",\n" +
            "  \"amount\": 10\n" +
            "}")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /transfer/{customerId}")})
    public ResponseEntity<TransactionResponse> transfer(@Valid @RequestBody TransactionRequest transactionRequest, @PathVariable Long customerId) {
        return transactionsService.transfer(transactionRequest, customerId);
    }
}