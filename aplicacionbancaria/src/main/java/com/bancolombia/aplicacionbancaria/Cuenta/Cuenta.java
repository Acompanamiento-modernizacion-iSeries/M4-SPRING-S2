package com.bancolombia.aplicacionbancaria.Cuenta;

import java.math.BigDecimal;

public class Cuenta {

    private String cuenta;
    private BigDecimal saldo;

    public Cuenta(String cuenta, BigDecimal saldo) {
        this.cuenta = cuenta;
        this.saldo = saldo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal deposito(BigDecimal monto){
            saldo = saldo.add(monto);
            return saldo;
    }

    public BigDecimal retiro(BigDecimal monto){
        saldo = saldo.subtract(monto);
        return saldo;
    }
}
