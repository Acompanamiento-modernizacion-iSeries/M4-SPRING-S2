package co.bancolombia.aplicacionbancaria.controller;

import co.bancolombia.aplicacionbancaria.repository.CuentaBancariaBD;
import co.bancolombia.aplicacionbancaria.model.CuentaBancaria;
import co.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    //Obtener saldo de una cuenta.
    @GetMapping("/saldo/{nroCuenta}")
    public String obtenerSaldo(@PathVariable String nroCuenta) {
       return cuentaService.obtenerSaldo(nroCuenta);
    }

    //Depositar a una cuenta.
    @PostMapping("/deposito/{nroCuenta}/{monto}")
    public String depositar(@PathVariable String nroCuenta, @PathVariable BigDecimal monto) {
       return cuentaService.depositar(nroCuenta, monto);
    }

    //Retirar de una cuenta.
    @PostMapping("/retiro/{nroCuenta}/{monto}")
    public String retirar(@PathVariable String nroCuenta, @PathVariable BigDecimal monto) {
        return cuentaService.retirar(nroCuenta, monto);
    }
}
