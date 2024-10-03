package com.taller2.springboot.app.controlador;

import com.taller2.springboot.app.servicio.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    // Aqui realizo la inyección de dependencias
    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    // Endpoint para consultar saldo
    @GetMapping("/saldo/{id}")
    public double consultarSaldo(@PathVariable Long id) {
        return cuentaService.consultarSaldo(id);
    }

    // Endpoint para realizar un depósito
    @PostMapping("/depositar/{id}")
    public String depositar(@PathVariable Long id, @RequestParam double monto) {
        cuentaService.depositar(id, monto);
        return "Depósito realizado con éxito.";
    }

    // Endpoint para realizar un retiro
    @PostMapping("/retirar/{id}")
    public String retirar(@PathVariable Long id, @RequestParam double monto) {
        cuentaService.retirar(id, monto);
        return "Retiro realizado con éxito.";
    }
}
