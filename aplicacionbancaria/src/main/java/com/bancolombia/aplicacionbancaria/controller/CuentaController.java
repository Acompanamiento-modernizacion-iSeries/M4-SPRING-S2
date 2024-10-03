package com.bancolombia.aplicacionbancaria.controller;
import com.bancolombia.aplicacionbancaria.repository.CuentaDB;
import com.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/saldo")
    public ResponseEntity<String> getSaldo(@RequestParam String numeroCuenta) {
        double saldo = cuentaService.consultarSaldo(numeroCuenta);
        return new ResponseEntity<>("El saldo de la cuenta: " + numeroCuenta + " es: " + saldo, HttpStatus.OK);
    }

    @PostMapping("/deposito")
    public ResponseEntity<String> realizarDeposito(@RequestParam String numeroCuenta, @RequestParam double monto) {
        cuentaService.depositar(numeroCuenta, monto);
        return new ResponseEntity<>("Depósito realizado con éxito", HttpStatus.OK);
    }

    @PostMapping("/retiro")
    public ResponseEntity<String> realizarRetiro(@RequestParam String numeroCuenta, @RequestParam double monto) {
        cuentaService.retirar(numeroCuenta, monto);
        return new ResponseEntity<>("Retiro realizado con éxito", HttpStatus.OK);
    }

}

