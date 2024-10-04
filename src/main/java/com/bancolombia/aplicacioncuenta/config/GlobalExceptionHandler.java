package com.bancolombia.aplicacioncuenta.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> manejarArgumentoInvalido(IllegalArgumentException exception) {
        return new ResponseEntity<>("Argumento inválido: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> manejarEstadoIlegal(IllegalStateException exception) {
        return new ResponseEntity<>("Estado ilegal: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> manejarNullPointer(NullPointerException exception) {
        return new ResponseEntity<>("Error inesperado: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErroresInesperados(Exception exception) {
        return new ResponseEntity<>("Error inesperado: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
