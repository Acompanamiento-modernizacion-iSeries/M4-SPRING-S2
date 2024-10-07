package com.bancolombia.aplicacionbancaria.controller;

import com.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/{numeroCuenta}/saldo")
    public BigDecimal obtenerSaldo(@PathVariable String numeroCuenta) {
        return cuentaService.obtenerSaldo(numeroCuenta);
    }

    @GetMapping("/{numeroCuenta}/depositourl/{monto}")
    public String despositar(@PathVariable String numeroCuenta, @PathVariable BigDecimal monto) {
        return cuentaService.depositar(numeroCuenta, monto).toString();
    }

    @PostMapping("/{numeroCuenta}/depositobody")
    public String despositarbody(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        return cuentaService.depositar(numeroCuenta, monto).toString();
    }

    @PostMapping("/{numeroCuenta}/retirarbody")
    public String retirarbody(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        return cuentaService.retirar(numeroCuenta, monto).toString();
    }

}
