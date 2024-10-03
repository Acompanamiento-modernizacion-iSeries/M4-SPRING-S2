package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.cuenta.Cuenta;
import com.bancolombia.aplicacionbancaria.repository.CuentaDB;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    private final CuentaDB cuentaDB;

    public CuentaService(CuentaDB cuentaDB) {
        this.cuentaDB = cuentaDB;
    }

    public double consultarSaldo(String numeroCuenta) {
        Cuenta cuenta = cuentaDB.getCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new NullPointerException("Cuenta no encontrada");
        }
        return cuenta.getSaldo();
    }

    public void depositar(String numeroCuenta, double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor que cero");
        }
        Cuenta cuenta = cuentaDB.getCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new NullPointerException("Cuenta no encontrada");
        }
        cuenta.setSaldo(cuenta.getSaldo() + monto);
    }

    public void retirar(String numeroCuenta, double monto) {
        Cuenta cuenta = cuentaDB.getCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new NullPointerException("Cuenta no encontrada");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor que cero");
        }
        if (cuenta.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);
    }
}

