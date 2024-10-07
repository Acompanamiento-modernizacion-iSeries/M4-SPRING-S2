package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.CuentaDb.CuentaDb;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class CuentaService {


    private final CuentaDb cuentaDb;
    private BigDecimal nuevoSaldo;
    private BigDecimal monto1;

    public CuentaService(CuentaDb cuentaDb) {
        this.cuentaDb = cuentaDb;
    }

    public BigDecimal obtenerSaldo(String cuenta){
        if (CuentaDb.BuscarCuenta(cuenta) == null){
            throw new NullPointerException("Cuenta no existe");
        };
        return CuentaDb.BuscarCuenta(cuenta).getSaldo();
    }

    public BigDecimal depositar(String cuenta, String monto){
        if (CuentaDb.BuscarCuenta(cuenta) == null){
            throw new NullPointerException("Cuenta no existe");
        };
        if (!esNumeroValido(monto)) {
            throw new IllegalArgumentException("El monto debe ser un valor numérico válido.");
        }

        monto1 = new BigDecimal(monto);

        if(monto1.compareTo(BigDecimal.ZERO) <= 0 ){
          throw new IllegalArgumentException("valor debe ser mayor a $0");
        }

        nuevoSaldo = cuentaDb.BuscarCuenta(cuenta).deposito(monto1);
        return nuevoSaldo;
    }

    public BigDecimal retirar(String cuenta, String monto){
        if (CuentaDb.BuscarCuenta(cuenta) == null){
            throw new NullPointerException("Cuenta no existe");
        }
        
        if (!esNumeroValido(monto)) {
            throw new IllegalArgumentException("El monto debe ser un valor numérico válido.");
        }
        monto1 = new BigDecimal(monto);
        if(monto1.compareTo(CuentaDb.BuscarCuenta(cuenta).getSaldo()) > 0 ){
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        nuevoSaldo = cuentaDb.BuscarCuenta(cuenta).retiro(monto1);
        return nuevoSaldo;
    }

    private boolean esNumeroValido(String montoStr) {
        // Regex para validar números decimales positivos
        return montoStr.matches("^\\d+(\\.\\d{1,2})?$");
    }
}
