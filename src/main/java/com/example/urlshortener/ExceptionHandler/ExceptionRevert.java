package com.example.urlshortener.ExceptionHandler;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.w3c.dom.html.HTMLTableCaptionElement;

@ControllerAdvice
public class ExceptionRevert {
    @ExceptionHandler
    ResponseEntity<ExceptionEntity> responseEntity(Exception exception){
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setErrorcode(HttpStatus.NOT_FOUND.value());
        exceptionEntity.setTimestamp(System.currentTimeMillis());
        exceptionEntity.setErrorMessage("Resource not Found");
        return new ResponseEntity<>(exceptionEntity , HttpStatus.NOT_FOUND);
    }

}
