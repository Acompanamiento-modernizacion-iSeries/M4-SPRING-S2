package co.bancolombia.practica2.service;

import co.bancolombia.practica2.cuenta.Cuenta;
import co.bancolombia.practica2.DB.CuentasDB;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentasDB cuentasDB;

    public CuentaService(CuentasDB cuentasDB) {
        this.cuentasDB = cuentasDB;
    }

    private Optional<Cuenta> findCuentaByNumero(String numeroCuenta) {
        return cuentasDB.cuentas.stream()
                .filter(cuenta -> cuenta.getNumeroCuenta().equals(numeroCuenta))
                .findFirst();
    }


    public BigDecimal getSaldo(String numeroCuenta) {
        Optional<Cuenta> cuenta = findCuentaByNumero(numeroCuenta);
        return cuenta.orElseThrow(() -> new RuntimeException("Cuenta no encontrada.")).getSaldo();
    }


    public BigDecimal deposito(String numeroCuenta, BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de depósito debe ser mayor que cero.");
        }
        Optional<Cuenta> cuenta = findCuentaByNumero(numeroCuenta);
        Cuenta c = cuenta.orElseThrow(() -> new RuntimeException("Cuenta no encontrada."));
        c.setSaldo(c.getSaldo().add(cantidad));
        return c.getSaldo();
    }


    public BigDecimal retiro(String numeroCuenta, BigDecimal cantidad) {
        Optional<Cuenta> cuenta = findCuentaByNumero(numeroCuenta);
        Cuenta c = cuenta.orElseThrow(() -> new RuntimeException("Cuenta no encontrada."));
        BigDecimal saldoActual = c.getSaldo();
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de retiro debe ser mayor que cero.");
        }
        if (saldoActual.compareTo(cantidad) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        c.setSaldo(saldoActual.subtract(cantidad));
        return c.getSaldo();
    }


}
