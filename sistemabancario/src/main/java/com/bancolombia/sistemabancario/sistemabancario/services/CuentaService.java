package com.bancolombia.sistemabancario.sistemabancario.services;

import com.bancolombia.sistemabancario.sistemabancario.models.Cuenta;
import com.bancolombia.sistemabancario.sistemabancario.repository.CuentasRepositorio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CuentaService {

    private CuentasRepositorio cuentaRepository ;
    public CuentaService(CuentasRepositorio cuentas){
        this.cuentaRepository = cuentas;
    }

    public List<Cuenta> listarCuentas(){
        return cuentaRepository.listarCuentas();
    }

    public Cuenta BuscarPorCuenta(String numeroCuenta){
        Cuenta cuenta = cuentaRepository.buscarPorCuenta(numeroCuenta);
        if(cuenta == null){
            throw new NullPointerException("La cuenta:" + numeroCuenta + " no existe en el sistema");
        }
        return cuenta;
    }

    public BigDecimal deposito(String numeroCuenta, BigDecimal monto){
        Cuenta cuenta  = BuscarPorCuenta(numeroCuenta);
        try{
            BigDecimal montoValidation = monto;
        }catch (NumberFormatException e){
            throw new NumberFormatException("El valor del monto debe ser númerico");
        }

        if(monto.compareTo(BigDecimal.ZERO)<= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }
        cuenta.setSaldo(cuenta.getSaldo().add(monto));
        return cuenta.getSaldo();
    }

    public BigDecimal retiro(String numeroCuenta, BigDecimal monto){
        Cuenta cuenta  = BuscarPorCuenta(numeroCuenta);
        try{
            BigDecimal montoValidation = monto;
        }catch (NumberFormatException e){
            throw new NumberFormatException("El valor del monto debe ser númerico");
        }

        if (monto.compareTo(BigDecimal.ZERO)<= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }

        if(cuenta.getSaldo().compareTo(monto)< 0){
            throw new IllegalArgumentException("El saldo es insuficiente para realizar el retiro");
        }
        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));

        return cuenta.getSaldo();
    }

}
