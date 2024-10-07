package com.bancolombia.aplicacionbancaria.CuentaDb;

import com.bancolombia.aplicacionbancaria.Cuenta.Cuenta;
import com.bancolombia.aplicacionbancaria.service.CuentaService;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CuentaDb {
    public static List<Cuenta> listaCb = //new ArrayList<>(
            List.of(
                    new Cuenta("1",new BigDecimal(100)),
                    new Cuenta("2",new BigDecimal(2000.00))
            );
    //);

    public static Cuenta BuscarCuenta(String numCuenta){

        for (Cuenta cuenta1 : listaCb){
            if(cuenta1.getCuenta().equals(numCuenta)){
                return cuenta1;
            }
        }
        return null;
    }


}
