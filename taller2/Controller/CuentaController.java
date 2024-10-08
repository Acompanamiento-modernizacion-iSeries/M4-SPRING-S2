package com.banco.taller2.Controller;
import com.banco.taller2.Service.CuentaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")

public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService){
        this.cuentaService = cuentaService;
    }

    @GetMapping("/saldo/{numeroCuenta}")
    public BigDecimal obtenerSaldo(@PathVariable String numeroCuenta){
        return cuentaService.obtenerSaldo(numeroCuenta);
    }


    @PostMapping("/deposito")
    public String depositar(@RequestParam String numeroCuenta, @RequestParam BigDecimal monto){
        return cuentaService.depositar(numeroCuenta, monto);

    }

    @PostMapping("/retiro")
    public String retirar(@RequestParam String numeroCuenta, @RequestParam BigDecimal monto){
        return cuentaService.retirar(numeroCuenta, monto);
    }


}


