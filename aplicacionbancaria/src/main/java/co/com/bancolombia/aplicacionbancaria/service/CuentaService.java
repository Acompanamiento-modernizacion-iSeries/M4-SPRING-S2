package co.com.bancolombia.aplicacionbancaria.service;



import co.com.bancolombia.aplicacionbancaria.cuenta.Cuenta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CuentaService {
    private Map<String, Cuenta> cuentas = new HashMap<>();

    public CuentaService() {
        cuentas.put("11111", new Cuenta("11111", new BigDecimal("50000.00")));
        cuentas.put("22222", new Cuenta("22222", new BigDecimal("100000.00")));
        cuentas.put("33333", new Cuenta("33333", new BigDecimal("60000.00")));
        cuentas.put("44444", new Cuenta("44444", new BigDecimal("200000.00")));
    }

    public BigDecimal obtenerSaldo(String numeroCuenta) {
        return cuentas.get(numeroCuenta).getSaldo();
    }

    public String depositar(String numeroCuenta, BigDecimal monto) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
        cuenta.setSaldo(cuenta.getSaldo().add(monto));
        return "Depósito exitoso. Saldo actual: " + cuenta.getSaldo();
    }

    public String retirar(String numeroCuenta, BigDecimal monto) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("No se permiten retiros con valores negativos.");
        }
        if (cuenta.getSaldo().compareTo(monto) < 0) {
            throw new IllegalArgumentException("No se permiten saldos negativos.");
        }
        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
        return "Retiro exitoso. Saldo actual: " + cuenta.getSaldo();
    }
}