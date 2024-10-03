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

    public String obtenerSaldo(Integer nroCuenta) {
        CuentaBancaria cuenta = validarNumeroCuenta(nroCuenta);
        return "El saldo de la cuenta número: " +nroCuenta+ " es: $" +cuenta.consultarSaldo();
    }

    public String depositar(Integer nroCuenta, BigDecimal monto) {
        CuentaBancaria cuenta = validarNumeroCuenta(nroCuenta);
        validarMonto(monto);
        cuenta.deposito(monto);
        return "¡Depósito exitoso! sobre cuenta número: " + nroCuenta + " Nuevo saldo: $" + cuenta.consultarSaldo();
    }

    public String retirar(Integer nroCuenta, BigDecimal monto) {
        CuentaBancaria cuenta = validarNumeroCuenta(nroCuenta);
        validarMonto(monto);
        if (cuenta.consultarSaldo().compareTo(monto) < 0) {
            throw new IllegalArgumentException("¡Saldo insuficiente para realizar retiro!");
        }
        cuenta.retiro(monto);
        return "¡Retiro exitoso! "+ "sobre cuenta número: "+nroCuenta+" Nuevo saldo: $" + cuenta.consultarSaldo();

    }

    private CuentaBancaria validarNumeroCuenta(Integer nroCuenta) {
        if (nroCuenta <= 0) {
            throw new IllegalArgumentException("¡El número de cuenta no puede ser menor o igual a cero!");
        }
        CuentaBancaria cuenta = CuentaBancariaBD.buscarCuenta(nroCuenta);
        if (cuenta == null) {
            throw new NullPointerException("¡La cuenta consultada no existe!");
        }
        return cuenta;
    }

    private void validarMonto(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("¡El monto debe ser mayor a cero!");
        }
    }

}
