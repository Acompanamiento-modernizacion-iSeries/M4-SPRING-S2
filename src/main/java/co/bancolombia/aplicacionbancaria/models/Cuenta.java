package co.bancolombia.aplicacionbancaria.models;


import java.math.BigDecimal;

public class Cuenta {

    private String cuenta;
    private BigDecimal saldo;
    private String tipoCuenta;


    public Cuenta() {
    }
    public Cuenta(String cuenta, BigDecimal saldo, String tipoCuenta) {
        this.cuenta = cuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
    }
    public String getCuenta() {
        return cuenta;
    }
    public void setCuenta(String cuenta){
        this.cuenta = cuenta;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}
