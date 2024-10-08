package com.bancolombia.aplicacionbancaria.controller;

import com.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/saldo")
    public String saldo(@RequestParam String cuenta) {
        return "Su saldo actual es $"
                + cuentaService.consultarSaldo(cuenta);
    }

    @PostMapping("/deposito")
    public String deposito(@RequestParam String cuenta, @RequestParam String monto) {
        return "Deposito realizado con éxito! "
                + "- Nuevo saldo: $" + cuentaService.deposito(cuenta, monto);
    }

    @PostMapping("/retiro")
    public String retiro(@RequestParam String cuenta, @RequestParam String monto) {
        return "Retiro realizado con éxito! "
                + "- Nuevo saldo: $" + cuentaService.retiro(cuenta, monto);
    }
}
