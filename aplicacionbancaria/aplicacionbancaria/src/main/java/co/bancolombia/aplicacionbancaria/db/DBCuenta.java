package co.bancolombia.aplicacionbancaria.db;

import co.bancolombia.aplicacionbancaria.modelo.CuentaBanco;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DBCuenta {
    private static List<CuentaBanco> cuentas  = new ArrayList<>();

    DBCuenta (){
        cuentas.add(new CuentaBanco("012345", new BigDecimal("1000")));
        cuentas.add(new CuentaBanco("678910", new BigDecimal("800")));
        cuentas.add(new CuentaBanco("111213", new BigDecimal("3200")));
        cuentas.add(new CuentaBanco("141516", new BigDecimal("2100")));
    }

    public static CuentaBanco buscarCuenta(String nroCuenta) {
        for (CuentaBanco cuenta : cuentas) {
            if (cuenta.getNroCuenta().equals(nroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }
}
