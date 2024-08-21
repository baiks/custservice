package com.customer.service.exception.advice;

import com.customer.service.exception.CustomException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import com.customer.service.exception.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;


@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends MessageSourceAdviceCtrl {

    protected GlobalExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleExceptionDataIntegrityViolationException(
            DataIntegrityViolationException e) {
        String message = e.getCause().getLocalizedMessage();
        log.error("Invalid input " + message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), message));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException ex, WebRequest request) {

        // Customize this message for clarity
        String message = "Database integrity constraint violation: " + ex.getMessage();

        // Log the exception
        logger.error("SQLIntegrityConstraintViolationException: ", ex);

        return new ResponseEntity<>(message, HttpStatus.CONFLICT); // 409 Conflict
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ErrorResponse> handleExceptionMismatchedInputException(MismatchedInputException e) {
        String message = "Invalid Data types";
        log.error("Mismatched Input Exception..." + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), message));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleApiRequestException(ResponseStatusException e) {
        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        log.error("ResponseStatusException...{}", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), message));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleExceptionCustomException(CustomException e) {
        log.error("Custom Exception... " + e.getMessage());
        e.printStackTrace();
        log.error(e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage()));
    }

}
