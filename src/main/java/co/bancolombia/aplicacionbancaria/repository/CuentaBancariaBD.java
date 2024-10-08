package co.bancolombia.aplicacionbancaria.repository;

import co.bancolombia.aplicacionbancaria.model.CuentaBancaria;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CuentaBancariaBD {

    private static List<CuentaBancaria> cuentas  = new ArrayList<>();

    public CuentaBancariaBD(){
        cuentas.add(new CuentaBancaria("1128935", new BigDecimal("1200000")));
        cuentas.add(new CuentaBancaria("8776521", new BigDecimal("2000000")));
        cuentas.add(new CuentaBancaria("9872123", new BigDecimal("3000000")));
        cuentas.add(new CuentaBancaria("1276311", new BigDecimal("200000")));
        cuentas.add(new CuentaBancaria("9864123", new BigDecimal("10000")));
    }

    //Buscar cuenta por número de cuenta.
    public static CuentaBancaria buscarCuenta(String nroCuenta) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.consultarCuenta().equals(nroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

}
