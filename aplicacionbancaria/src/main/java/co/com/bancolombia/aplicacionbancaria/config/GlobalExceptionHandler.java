package co.com.bancolombia.aplicacionbancaria.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> manejarArgumentoInvalido(IllegalArgumentException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErroresInesperados(Exception exception){
        return new ResponseEntity<>("Esta excepcion fue capturada de forma generica", HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> manejarEstadoIlegal(IllegalStateException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);

    }
}