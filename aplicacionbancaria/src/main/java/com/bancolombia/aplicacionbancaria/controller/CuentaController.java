package com.bancolombia.aplicacionbancaria.controller;

import com.bancolombia.aplicacionbancaria.CuentaDb.CuentaDb;
import com.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

   private final CuentaService cuentaService;
    private BigDecimal nuevoSaldo;


    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }
    // private CuentaDb cuentaDb = new CuentaDb();

    @GetMapping("/saldo")
    public BigDecimal obtenerSaldo(@RequestParam String cuenta){
        BigDecimal saldo = cuentaService.obtenerSaldo(cuenta);
        return saldo;
    }

    //carga informacion por parametro
    /*@GetMapping("/deposito/{monto}")
    public String depositar(@PathVariable BigDecimal monto){
        return "Deposito realizado con exito: " ;
    }*/

    //carga informacion por body
   @PostMapping("/deposito")
    public String DepositarSaldo(@RequestParam String cuenta, String monto){
        nuevoSaldo = cuentaService.depositar(cuenta, monto);
        return "Deposito exitoso, saldo actual: " + nuevoSaldo ;
    }

    @PostMapping("/retiro")
    public String RetiroSaldo(@RequestParam String cuenta, String monto){
       nuevoSaldo = cuentaService.retirar(cuenta, monto);
        return "Retiro exitoso, saldo actual: " + nuevoSaldo ;
    }
}
