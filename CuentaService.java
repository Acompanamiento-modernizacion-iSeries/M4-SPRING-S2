package co.bancolombia.aplicacionbancaria.service;

import co.bancolombia.aplicacionbancaria.model.CuentaBancaria;
import co.bancolombia.aplicacionbancaria.repository.CuentaBancariabasedatos;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaService {

    private final CuentaBancariabasedatos CuentaBancariabasedatos;

    public CuentaService(CuentaBancariabasedatos CuentaBancariabasedatos) {
        this.CuentaBancariabasedatos = CuentaBancariabasedatos;
    }

    public String obtenerSaldo(String nroCuenta) {
        CuentaBancaria cuenta = CuentaBancariabasedatos.buscarCuenta(nroCuenta);
        if (cuenta == null) {
            throw new NullPointerException("La cuenta no existe");
        }
        return "Cuenta número:  " +nroCuenta+ " con saldo: $" +cuenta.consultarSaldo();
    }

    public String depositar(String nroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero");
        }
        CuentaBancaria cuenta = CuentaBancariabasedatos.buscarCuenta(nroCuenta);
        if (cuenta == null) {
            throw new NullPointerException("La cuenta no existe!");
        }
        cuenta.deposito(monto);
        return "Depósito exitoso "+ "sobre cuenta número: "+nroCuenta+" con saldo: $" + cuenta.consultarSaldo();

    }

    public String retirar(String nroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
        }
        CuentaBancaria cuenta = CuentaBancariabasedatos.buscarCuenta(nroCuenta);
        if (cuenta == null) {
            throw new NullPointerException("La cuenta no existe");
        }
        if (cuenta.consultarSaldo().compareTo(monto) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar retiro");
        }
        cuenta.retiro(monto);
        return "Retiro exitoso "+ "sobre cuenta número: "+nroCuenta+" con saldo: $" + cuenta.consultarSaldo();

    }


}
