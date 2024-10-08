package com.banco.taller2.Service;

import com.banco.taller2.Model.Cuenta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CuentaService {

    private List<Cuenta> cuentas;

    public CuentaService() {
        this.cuentas = Cuenta.crearCuentas();
    }

    public BigDecimal obtenerSaldo(String numeroCuenta){
        Cuenta cuenta = encontrarCuenta(numeroCuenta);
        return cuenta.getSaldo();
    }

    public String depositar(String numeroCuenta, BigDecimal monto){
        Cuenta cuenta = encontrarCuenta(numeroCuenta);
        if(monto.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
        cuenta.setSaldo(cuenta.getSaldo().add(monto));
        return "Depósito realizado. Saldo actual: $" + cuenta.getSaldo();
    }

    public String retirar(String numeroCuenta, BigDecimal monto){
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
        Cuenta cuenta = encontrarCuenta(numeroCuenta);
        if (cuenta.getSaldo().compareTo(monto) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
        return "Retiro realizado. Saldo actual: $" + cuenta.getSaldo();
    }

    private Cuenta encontrarCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        throw new RuntimeException("Cuenta no encontrada");
    }
}
