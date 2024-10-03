package com.bancolombia.sistemabancario.sistemabancario.controllers;


import java.math.BigDecimal;
import java.util.List;

import com.bancolombia.sistemabancario.sistemabancario.services.CuentaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.sistemabancario.sistemabancario.models.Cuenta;
import com.bancolombia.sistemabancario.sistemabancario.repository.CuentasRepositorio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/cuenta")
public class CuentaConttroller {

    private CuentaService cuentaService;
    public CuentaConttroller(CuentaService cuentaService){
        this.cuentaService = cuentaService;
    }

    @GetMapping("/listar")
    public List<Cuenta> listarCuentas() {
        List<Cuenta> listacuentas = cuentaService.listarCuentas();
        return listacuentas;
    }
    

    @GetMapping("/saldo/{numeroCuenta}")
    public String saldo(@PathVariable("numeroCuenta") String numeroCuenta) {
        Cuenta cuenta = cuentaService.BuscarPorCuenta(numeroCuenta);
        return  "El saldo de la cuenta es de: " + cuenta.getSaldo();
    }

    @PostMapping("/deposito/{numeroCuenta}/{monto}")
    public String deposito(@PathVariable("numeroCuenta") String numeroCuenta, @PathVariable("monto") BigDecimal monto) {
        BigDecimal saldoActual = cuentaService.deposito(numeroCuenta, monto);
        return "Depósito exitoso, El saldo actual de su cuenta es de :" + saldoActual;
    }

    @PostMapping("/retiro/{numeroCuenta}/{monto}")
    public String retiro(@PathVariable("numeroCuenta") String numeroCuenta, @PathVariable("monto") BigDecimal monto) {
        BigDecimal saldoActual = cuentaService.retiro(numeroCuenta, monto);
        return "retiro exitoso, El saldo actual de su cuenta es de :" + saldoActual;
    }
    
}
