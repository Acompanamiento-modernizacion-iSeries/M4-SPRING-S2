package com.taller2.springboot.app.modelo;

public class Cuenta {
    private Long id;
    private String titular;
    private double saldo;

    // Constructor
    public Cuenta(Long id, String titular, double saldo) {
        this.id = id;
        this.titular = titular;
        this.saldo = saldo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}
