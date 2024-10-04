package co.bancolombia.aplicacionbancaria.repository;

import co.bancolombia.aplicacionbancaria.model.CuentaBancaria;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CuentaBancariabasedatos {

    private static List<CuentaBancaria> cuentas  = new ArrayList<>();

    public CuentaBancariabasedatos(){
        cuentas.add(new CuentaBancaria("9876543", new BigDecimal("9800000")));
        cuentas.add(new CuentaBancaria("8765432", new BigDecimal("87000000")));
        cuentas.add(new CuentaBancaria("7654321", new BigDecimal("76000000")));
        cuentas.add(new CuentaBancaria("6543219", new BigDecimal("6500000")));
        cuentas.add(new CuentaBancaria("5432198", new BigDecimal("540000")));
		cuentas.add(new CuentaBancaria("4321986", new BigDecimal("4300000")));
        cuentas.add(new CuentaBancaria("3219876", new BigDecimal("32000000")));
        cuentas.add(new CuentaBancaria("2198765", new BigDecimal("21000000")));
 
    }

    //Buscar cuenta.
    public static CuentaBancaria buscarCuenta(String nroCuenta) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.consultarCuenta().equals(nroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

}
