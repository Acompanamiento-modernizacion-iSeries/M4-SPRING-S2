package co.bancolombia.aplicacionbancaria.model;

import java.math.BigDecimal;

public class CuentaBancaria {

    private Integer nroCuenta;
    private BigDecimal saldo;

    public CuentaBancaria (Integer nroCuenta, BigDecimal saldo) {
        this.nroCuenta = nroCuenta;
        this.saldo = saldo;
    }

    public Integer consultarCuenta() {

        return nroCuenta;
    }

    public void asignarCuenta(Integer nroCuenta) {

        this.nroCuenta = nroCuenta;
    }

    public BigDecimal consultarSaldo() {

        return saldo;
    }

    public void asignarSaldo(BigDecimal saldo) {

        this.saldo = saldo;
    }

    public void deposito(BigDecimal monto) {
        saldo = saldo.add(monto);
    }

    public void retiro(BigDecimal monto) {
        saldo = saldo.subtract(monto);
    }
}
