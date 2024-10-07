package co.bancolombia.practica2.cuenta;


import co.bancolombia.practica2.DB.CuentasDB;
import co.bancolombia.practica2.service.CuentaService;
import java.math.BigDecimal;

public class Cuenta {

    private String numeroCuenta;
    private BigDecimal saldo;

    public Cuenta(String numeroCuenta, BigDecimal saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }


    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}