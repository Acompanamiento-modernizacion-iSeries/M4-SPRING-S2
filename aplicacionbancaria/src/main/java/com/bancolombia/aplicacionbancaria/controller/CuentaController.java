package com.bancolombia.aplicacionbancaria.controller;

import com.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/{cuenta}")
public class CuentaController {

    private CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/saldo")
    public String obtenerSaldo(@RequestParam String cuenta) {
        return cuentaService.obtenerSaldo(cuenta);
    }

    @PostMapping("/deposito/{monto}")
    public String depositar(@RequestParam String cuenta, BigDecimal monto) {
        return cuentaService.depositar(cuenta, monto);
    }

    @PostMapping("/retiro/{monto}")
    public String retirar(@RequestParam BigDecimal  monto, @RequestParam String cuenta) {
        return cuentaService.retirar(monto, Integer.parseInt(cuenta));
    }

}
