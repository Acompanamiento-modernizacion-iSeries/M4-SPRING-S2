package co.bancolombia.aplicacionbancaria.service;

import co.bancolombia.aplicacionbancaria.model.CuentaBancaria;
import co.bancolombia.aplicacionbancaria.repository.CuentaBancariaBD;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaService {

    private final CuentaBancariaBD CuentaBancariaBD;

    public CuentaService(CuentaBancariaBD CuentaBancariaBD) {
        this.CuentaBancariaBD = CuentaBancariaBD;
    }

    public String obtenerSaldo(String nroCuenta) {
        CuentaBancaria cuenta = CuentaBancariaBD.buscarCuenta(nroCuenta);
        if (cuenta == null) {
            throw new NullPointerException("¡La cuenta consultada no existe!");
        }
        return "El saldo de la cuenta número: " +nroCuenta+ " es: $" +cuenta.consultarSaldo();
    }

    public String depositar(String nroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("¡El monto a depositar debe ser mayor a cero!");
        }
        CuentaBancaria cuenta = CuentaBancariaBD.buscarCuenta(nroCuenta);
        if (cuenta == null) {
            throw new NullPointerException("¡La cuenta consultada no existe!");
        }
        cuenta.deposito(monto);
        return "¡Depósito exitoso! "+ "sobre cuenta número: "+nroCuenta+" Nuevo saldo: $" + cuenta.consultarSaldo();

    }

    public String retirar(String nroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("¡El monto a retirar debe ser mayor a cero!");
        }
        CuentaBancaria cuenta = CuentaBancariaBD.buscarCuenta(nroCuenta);
        if (cuenta == null) {
            throw new NullPointerException("¡La cuenta consultada no existe!");
        }
        if (cuenta.consultarSaldo().compareTo(monto) < 0) {
            throw new IllegalArgumentException("¡Saldo insuficiente para realizar retiro!");
        }
        cuenta.retiro(monto);
        return "¡Retiro exitoso! "+ "sobre cuenta número: "+nroCuenta+" Nuevo saldo: $" + cuenta.consultarSaldo();

    }


}
