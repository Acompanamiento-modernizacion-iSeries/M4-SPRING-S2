package co.bancolombia.aplicacionbancaria.Controller;

import co.bancolombia.aplicacionbancaria.CuentaService.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/{numeroCuenta}/saldo")
    public BigDecimal getSaldo(@PathVariable String numeroCuenta) {
        return cuentaService.getSaldo(numeroCuenta);
    }

    @PostMapping("/{numeroCuenta}/deposito")
    public String depositar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        cuentaService.depositar(numeroCuenta, monto);
        BigDecimal saldoActual = cuentaService.getSaldo(numeroCuenta);
        return "Depósito exitoso. Saldo actual: " + saldoActual;
    }

    @PostMapping("/{numeroCuenta}/retiro")
    public String retirar(@PathVariable String numeroCuenta, @RequestParam BigDecimal monto) {
        cuentaService.retirar(numeroCuenta, monto);
        BigDecimal saldoActual = cuentaService.getSaldo(numeroCuenta);
        return "Retiro exitoso. Saldo actual: " + saldoActual;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}