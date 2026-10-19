package com.example.be_done_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ProblemDetail> handleNotFoundException(NotFound ex) {
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,ex.getMessage());
        problemDetail.setTitle("Not found");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }
    @ExceptionHandler(WrongCredentials.class)
    public ResponseEntity<ProblemDetail> handleWrongCredentialsException(WrongCredentials ex) {
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,ex.getMessage());
        problemDetail.setTitle("Wrong credentials");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }
}
