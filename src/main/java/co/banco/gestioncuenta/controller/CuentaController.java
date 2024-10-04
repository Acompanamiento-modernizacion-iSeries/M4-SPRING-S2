package co.banco.gestioncuenta.controller;

import co.banco.gestioncuenta.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    //@Autowired
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/{numeroCuenta}/saldo")
    public BigDecimal obtenerSaldo(@PathVariable String numeroCuenta) {
        return cuentaService.obtenerSaldo(numeroCuenta);
    }

    @PostMapping("/{numeroCuenta}/deposito")
    public BigDecimal depositar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        return cuentaService.depositar(numeroCuenta, monto);
    }

    @PostMapping("/{numeroCuenta}/retiro")
    public BigDecimal retirar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        return cuentaService.retirar(numeroCuenta, monto);
    }
}