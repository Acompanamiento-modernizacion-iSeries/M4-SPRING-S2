package co.bancolombia.aplicacionbancaria.controller;

import co.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/saldo/{numeroCuenta}")
    public BigDecimal obtenerSaldo(@PathVariable String numeroCuenta) {
        return cuentaService.obtenerSaldo(numeroCuenta);
    }

    @PostMapping("/deposito/{numeroCuenta}")
    public ResponseEntity<String> depositar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        cuentaService.depositar(numeroCuenta, monto);
        return ResponseEntity.ok("Depósito realizado exitosamente.");
    }

    @PostMapping("/retiro/{numeroCuenta}")
    public ResponseEntity<String> retirar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        if (cuentaService.retirar(numeroCuenta, monto)) {
            return ResponseEntity.ok("Retiro realizado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente.");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> manejarExcepcion(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
