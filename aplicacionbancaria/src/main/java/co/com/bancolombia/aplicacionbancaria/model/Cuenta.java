package co.com.bancolombia.aplicacionbancaria.model;

import java.math.BigDecimal;

public class Cuenta {
    protected String numeroCuenta;
    protected BigDecimal saldo;

    public Cuenta(String numeroCuenta, BigDecimal saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public String numeroCuenta() {
        return numeroCuenta;
    }

    public BigDecimal saldo() {
        return saldo;
    }

    public void actualizarSaldo(BigDecimal nuevoSaldo) {
        this.saldo = nuevoSaldo;
    }
}
