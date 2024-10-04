
package com.bancolombia.aplicacioncuenta.service;

import org.springframework.stereotype.Service;

import com.bancolombia.aplicacioncuenta.model.Cuenta;
import com.bancolombia.aplicacioncuenta.repository.CuentasDB;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    public BigDecimal obtenerSaldo(String idCuenta) {
        Cuenta cuenta = buscarCuentaPorId(idCuenta);
        return cuenta.getSaldo();
    }

    public BigDecimal depositar(String idCuenta, BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
        Cuenta cuenta = buscarCuentaPorId(idCuenta);
        BigDecimal nuevoSaldo = cuenta.getSaldo().add(monto);
        cuenta.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    public BigDecimal retirar(String idCuenta, BigDecimal monto) {
        if (monto == null) {
            throw new IllegalArgumentException("El monto no puede ser nulo");
        }
        Cuenta cuenta = buscarCuentaPorId(idCuenta);
        if (monto.compareTo(cuenta.getSaldo()) > 0) {
            throw new IllegalStateException("Saldo insuficiente para ese retiro");
        }

        BigDecimal nuevoSaldo = cuenta.getSaldo().subtract(monto);
        cuenta.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    private Cuenta buscarCuentaPorId(String idCuenta) {
        Optional<Cuenta> cuenta = CuentasDB.cuentas.stream()
                .filter(c -> c.getId().equals(idCuenta))
                .findFirst();

        if (cuenta.isEmpty()) {
            throw new IllegalArgumentException("Cuenta no encontrada con ID: " + idCuenta);
        }

        return cuenta.get();
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return CuentasDB.cuentas;
    }
}
