package co.bancolombia.aplicacionbancaria.service;

import co.bancolombia.aplicacionbancaria.db.DBCuenta;
import co.bancolombia.aplicacionbancaria.modelo.CuentaBanco;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaService {

    private final DBCuenta dbCuenta;

    public CuentaService(DBCuenta dbCuenta) {
        this.dbCuenta = dbCuenta;
    }


    public String consultarSaldo(String nroCuenta) {
        CuentaBanco cuenta = DBCuenta.buscarCuenta(nroCuenta);
        validarCuenta(cuenta);
        return "El saldo de la cuenta número: " +nroCuenta+ " es: $" +cuenta.getSaldo();
    }

    public String depositar(String nroCuenta, BigDecimal monto) {
        CuentaBanco cuenta = DBCuenta.buscarCuenta(nroCuenta);
        validarCuenta(cuenta);
        validarMonto(monto);
        cuenta.depositar(monto);
        return "¡Depósito exitoso! sobre cuenta número: " + nroCuenta + " Nuevo saldo: $" + cuenta.getSaldo();
    }

    public String retirar(String nroCuenta, BigDecimal monto) {
        CuentaBanco cuenta = DBCuenta.buscarCuenta(nroCuenta);
        validarCuenta(cuenta);
        validarMonto(monto);
        if (cuenta.getSaldo().compareTo(monto) < 0) {
            throw new IllegalArgumentException("¡Saldo insuficiente para realizar retiro!");
        }
        cuenta.retirar(monto);
        return "¡Retiro exitoso! "+ "sobre cuenta número: "+nroCuenta+" Nuevo saldo: $" + cuenta.getSaldo();
    }

    private void validarMonto(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("¡El monto debe ser mayor a cero!");
        }
    }

    private void validarCuenta(CuentaBanco cuenta){
        if (cuenta == null) {
            throw new NullPointerException("Cuenta no existe");
        }
    }


}
