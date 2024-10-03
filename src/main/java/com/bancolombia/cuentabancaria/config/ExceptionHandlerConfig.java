package com.bancolombia.cuentabancaria.config;

import com.bancolombia.cuentabancaria.model.DomainException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @Autowired
    private ObjectMapper mapper;

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleRequestApiException(
            HttpServletRequest request,
            DomainException domainException) {
        return new ResponseEntity<>(domainException.getErrorModel1(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
