package com.customer.service.controllers;


import com.customer.service.dtos.EditDto;
import com.customer.service.dtos.JwtResponse;
import com.customer.service.dtos.LoginDto;
import com.customer.service.dtos.SignupDto;
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
@Tag(name = "AUTH", description = "User is required to login in order to obtain JWT")
public class AuthController {


    private final AuthService authService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    @Operation(summary = "Create a user", description = "Returns the details of a user.\n" + "\n" + "Example Requests:\n" + "\n" + "user")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /signup")})
    public ResponseEntity<SignupDto> register(@Valid @RequestBody SignupDto signupDto) {
        return authService.signUp(signupDto);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @Operation(summary = "Login user", description = "Returns the JWT.\n" + "\n" + "Example Requests:\n" + "\n" + "jwt")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /login")})
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto loginDto) {
        return authService.authUser(loginDto);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/edit/:id")
    @Operation(summary = "Login user", description = "Returns the details of updated user.\n" + "\n" + "Example Requests:\n" + "\n" + "user")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "PATCH: /edit")})
    public ResponseEntity<EditDto> edit(@Valid @RequestBody EditDto editDto, @PathVariable Long id) {
        return authService.editUser(editDto, id);
    }
}