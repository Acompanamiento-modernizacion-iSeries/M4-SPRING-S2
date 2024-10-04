package co.com.bancolombia.aplicacionbancaria.controller;

import co.com.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService CUENTA_SERVICE;

    public CuentaController(CuentaService cuentaService) {
        this.CUENTA_SERVICE = cuentaService;
    }

    @GetMapping("/saldo")
    public String saldo(@RequestParam String cuenta) {
            return "Su saldo actual es $"
                    + CUENTA_SERVICE.consultarSaldo(cuenta);
    }

    @PostMapping("/deposito")
    public String deposito(@RequestParam String cuenta, @RequestParam String monto) {
            return "Deposito realizado con éxito! "
                    + "- Nuevo saldo: $" + CUENTA_SERVICE.deposito(cuenta, monto);
    }

    @PostMapping("/retiro")
    public String retiro(@RequestParam String cuenta, @RequestParam String monto) {
        return "Retiro realizado con éxito! "
                + "- Nuevo saldo: $" + CUENTA_SERVICE.retiro(cuenta, monto);
    }
}
