package com.bancolombia.aplicacionbancaria.repository;

import com.bancolombia.aplicacionbancaria.cuenta.Cuenta;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CuentaDB {
    private Map<String, Cuenta> cuentas;

    public CuentaDB() {
        cuentas = new HashMap<>();
        cuentas.put("CUENTA-1", new Cuenta("CUENTA-1", 1000));
        cuentas.put("CUENTA-2", new Cuenta("CUENTA-2", 2000));
        cuentas.put("CUENTA-3", new Cuenta("CUENTA-3", 3000));
        cuentas.put("CUENTA-4", new Cuenta("CUENTA-4", 4000));
        cuentas.put("CUENTA-5", new Cuenta("CUENTA-5", 5000));
        cuentas.put("CUENTA-6", new Cuenta("CUENTA-6", 6000));
    }

    public Map<String, Cuenta> getCuentas() {
        return cuentas;
    }

    public Cuenta getCuenta(String numeroCuenta) {
        return cuentas.get(numeroCuenta);
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.put(cuenta.getNumeroCuenta(), cuenta);
    }
}

