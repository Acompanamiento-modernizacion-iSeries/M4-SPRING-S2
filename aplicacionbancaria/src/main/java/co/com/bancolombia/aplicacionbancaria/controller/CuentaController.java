package co.com.bancolombia.aplicacionbancaria.controller;


import co.com.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/{numeroCuenta}/saldo")
    public BigDecimal getSaldo(@PathVariable String numeroCuenta) {
        return cuentaService.obtenerSaldo(numeroCuenta);
    }

    @PostMapping("/{numeroCuenta}/deposito")
    public String depositar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        cuentaService.depositar(numeroCuenta, monto);
        BigDecimal saldoActual = cuentaService.obtenerSaldo(numeroCuenta);
        return "Depósito exitoso. Saldo actual: " + saldoActual;
    }

    @PostMapping("/{numeroCuenta}/retiro")
    public String retirar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        cuentaService.retirar(numeroCuenta, monto);
        BigDecimal saldoActual = cuentaService.obtenerSaldo(numeroCuenta);
        return "Retiro exitoso. Saldo actual: " + saldoActual;
    }
}