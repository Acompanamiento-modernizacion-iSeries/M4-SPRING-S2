package co.bancolombia.aplicacionbancaria.service;

import co.bancolombia.aplicacionbancaria.model.Cuenta;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CuentaService {

    private Map<String, Cuenta> cuentas = new HashMap<>();

    public CuentaService() {
        // Pre-cargar cuentas
        cuentas.put("123456", new Cuenta("123456"));
        cuentas.put("654321", new Cuenta("654321"));
    }

    public BigDecimal obtenerSaldo(String numeroCuenta) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        return cuenta != null ? cuenta.getSaldo() : BigDecimal.ZERO;
    }

    public void depositar(String numeroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de depósito debe ser mayor que cero.");
        }
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null) {
            cuenta.depositar(monto);
        }
    }

    public boolean retirar(String numeroCuenta, BigDecimal monto) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null) {
            if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El monto de retiro debe ser mayor que cero.");
            }
            return cuenta.retirar(monto);
        }
        return false;
    }
}
