package com.example.urlshortener.ErrorHandeling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorRes {
    @ExceptionHandler
    ResponseEntity<Responseclass> responseEntity(Exception exception){
        Responseclass responseclass = new Responseclass();
        responseclass.setError("The Requested Resource is not found");
        responseclass.setTimestamp(System.currentTimeMillis());
        responseclass.setErrorid(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseclass,HttpStatus.NOT_FOUND);
    }
}
