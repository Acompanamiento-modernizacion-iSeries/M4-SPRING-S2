package com.taller2.springboot.app.servicio;

import com.taller2.springboot.app.excepciones.ExcepcionesPersonalizadas;
import com.taller2.springboot.app.modelo.Cuenta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {
    private List<Cuenta> cuentas;

    public CuentaService() {
        // Precargar cuentas en memoria
        cuentas = new ArrayList<>();
        cuentas.add(new Cuenta(1L, "Juan Pérez", 5000));
        cuentas.add(new Cuenta(2L, "Ana Gómez", 10000));
        cuentas.add(new Cuenta(3L, "William Sanchez", 30000));
    }

    // Consultar saldo
    public double consultarSaldo(Long id) {
        Cuenta cuenta = encontrarCuentaPorId(id);
        return cuenta.getSaldo();
    }

    // Depósito
    public void depositar(Long id, double monto) {
        if (monto <= 0) {
            throw new ExcepcionesPersonalizadas.DepositoInvalidoExcepcion("El monto de depósito debe ser positivo.");
        }
        Cuenta cuenta = encontrarCuentaPorId(id);
        cuenta.setSaldo(cuenta.getSaldo() + monto);
    }

    // Retiro
    public void retirar(Long id, double monto) {
        Cuenta cuenta = encontrarCuentaPorId(id);
        if (cuenta.getSaldo() < monto) {
            throw new ExcepcionesPersonalizadas.SaldoInsuficienteExcepcion("Saldo insuficiente.");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);
    }

    private Cuenta encontrarCuentaPorId(Long id) {
        Optional<Cuenta> cuentaOpt = cuentas.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (!cuentaOpt.isPresent()) {
            throw new ExcepcionesPersonalizadas.CuentaNoEncontradaExcepcion("Cuenta no encontrada.");
        }
        return cuentaOpt.get();
    }
}
