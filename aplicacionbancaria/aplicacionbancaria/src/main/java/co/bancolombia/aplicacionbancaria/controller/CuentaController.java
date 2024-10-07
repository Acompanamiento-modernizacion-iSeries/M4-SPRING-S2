package co.bancolombia.aplicacionbancaria.controller;

import co.bancolombia.aplicacionbancaria.db.DBCuenta;
import co.bancolombia.aplicacionbancaria.modelo.CuentaBanco;
import co.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    private final CuentaService cuentaService;

    CuentaController(CuentaService cuentaService){
        this.cuentaService = cuentaService;
    }

    @GetMapping("/saldo/{nroCuenta}")
    public String consultarSaldo(@PathVariable String nroCuenta) {
        return cuentaService.consultarSaldo(nroCuenta);
    }
    @PostMapping("/deposito/{nroCuenta}/{monto}")
    public String depositar(@PathVariable String nroCuenta, @PathVariable BigDecimal monto) {
       return cuentaService.depositar(nroCuenta, monto);
    }
    @PostMapping("/retiro/{nroCuenta}/{monto}")
    public String retirar(@PathVariable String nroCuenta, @PathVariable BigDecimal monto) {
        return cuentaService.retirar(nroCuenta, monto);
    }
}
