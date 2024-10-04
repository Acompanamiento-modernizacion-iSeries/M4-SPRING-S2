package com.bancolombia.aplicacioncuenta.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.aplicacioncuenta.model.Cuenta;
import com.bancolombia.aplicacioncuenta.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/saldo")
    public BigDecimal obtenerSaldo(@RequestParam String idCuenta) {
        return cuentaService.obtenerSaldo(idCuenta);
    }

    @PostMapping("/deposito")
    public String depositar(@RequestParam String idCuenta, @RequestParam BigDecimal monto) {
        try {
            BigDecimal nuevoSaldo = cuentaService.depositar(idCuenta, monto);
            return "Depósito exitoso. Saldo actual: " + nuevoSaldo;
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/retirar")
    public String retirar(@RequestParam String idCuenta, @RequestParam BigDecimal monto) {
        try {
            BigDecimal nuevoSaldo = cuentaService.retirar(idCuenta, monto);
            return "Retiro exitoso. Saldo actual: " + nuevoSaldo;
        } catch (IllegalStateException e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/cuentas")
    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaService.obtenerTodasLasCuentas();
    }
}
