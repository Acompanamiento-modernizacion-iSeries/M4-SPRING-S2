package co.bancolombia.practica2.controller;

import org.springframework.web.bind.annotation.*;
import co.bancolombia.practica2.service.CuentaService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }


    @GetMapping("/saldo/numeroCuenta/{numeroCuenta}")
    public BigDecimal getSaldo(@PathVariable String numeroCuenta) {
        return cuentaService.getSaldo(numeroCuenta);
    }


    @PostMapping("/deposito/numeroCuenta/{numeroCuenta}/cantidad/{cantidad}")
    public BigDecimal deposito(@PathVariable String numeroCuenta, @PathVariable BigDecimal cantidad) {
        return cuentaService.deposito(numeroCuenta, cantidad);
    }


    @PostMapping("/retiro/numeroCuenta/{numeroCuenta}/cantidad/{cantidad}")
    public BigDecimal retiro(@PathVariable String numeroCuenta, @PathVariable BigDecimal cantidad) {
        return cuentaService.retiro(numeroCuenta, cantidad);
    }
}
