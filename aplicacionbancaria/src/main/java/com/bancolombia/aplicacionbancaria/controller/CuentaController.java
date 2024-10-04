package com.bancolombia.aplicacionbancaria.controller;
import com.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/saldo")
    public ResponseEntity<String> obtenerSaldo(@RequestParam Long numeroCuenta) {
        BigDecimal saldo = cuentaService.consultarSaldo(numeroCuenta);
        return new ResponseEntity<>("El saldo de la cuenta es: " + saldo, HttpStatus.OK);
    }

    @PostMapping("/deposito")
    public ResponseEntity<String> depositar(@RequestParam Long numeroCuenta, @RequestParam BigDecimal cantidad) {
        boolean exito = cuentaService.depositar(numeroCuenta, cantidad);
        return new ResponseEntity<>("Depósito exitoso", HttpStatus.OK);
    }

    @PostMapping("/retiro")
    public ResponseEntity<String> retirar(@RequestParam Long numeroCuenta, @RequestParam BigDecimal cantidad) {
        boolean exito = cuentaService.retirar(numeroCuenta, cantidad);
        return new ResponseEntity<>("Retiro exitoso", HttpStatus.OK);
    }
}
