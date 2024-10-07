package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.model.Cuenta;
import com.bancolombia.aplicacionbancaria.repository.CuentasDB;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaService {

    private final CuentasDB cuentasDB;

    public CuentaService(CuentasDB cuentasDB) {
        this.cuentasDB = cuentasDB;
    }

    public BigDecimal obtenerSaldo(String numeroCuenta) {
        for (Cuenta cuenta : cuentasDB.cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta.getSaldo();
            }
        }
        throw new IllegalArgumentException("Cuenta no encontrada");
    }

    public BigDecimal depositar(String numeroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero");
        }
        for (Cuenta cuenta : cuentasDB.cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                cuenta.setSaldo(cuenta.getSaldo().add(monto));
                return cuenta.getSaldo();
            }
        }
        throw new IllegalArgumentException("Cuenta no encontrada");
    }

    public BigDecimal retirar(String numeroCuenta, BigDecimal monto) {
        for (Cuenta cuenta : cuentasDB.cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                if (monto.compareTo(cuenta.getSaldo()) > 0) {
                    throw new IllegalStateException("Saldo insuficiente para realizar el retiro");
                }
                cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
                return cuenta.getSaldo();
            }
        }
        throw new IllegalArgumentException("Cuenta no encontrada");
    }


}
