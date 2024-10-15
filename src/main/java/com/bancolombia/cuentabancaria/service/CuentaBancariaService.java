package com.bancolombia.cuentabancaria.service;

import com.bancolombia.cuentabancaria.model.CuentaBancariaEntity;
import com.bancolombia.cuentabancaria.repository.CuentasBancariasBD;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaBancariaService {

    private CuentasBancariasBD cuentasBancariasBD;

    public boolean validSaldo(BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        return true;
    }

    public CuentaBancariaEntity getCuenta(String cuenta){
        return cuentasBancariasBD.getCuenta(cuenta);
    }

    public CuentaBancariaEntity deposito(BigDecimal valor, String cuenta){
        CuentaBancariaEntity cuentaEntity = cuentasBancariasBD.getCuenta(cuenta);
        if(cuentaEntity == null){
            throw new NullPointerException("La Cuenta bancaria no existe");
        }else if(validSaldo(valor)){
            cuentaEntity.deposito(valor);
        }
        return cuentaEntity;
    }

    public CuentaBancariaEntity retiro(BigDecimal valor, String cuenta){
        CuentaBancariaEntity cuentaEntity = cuentasBancariasBD.getCuenta(cuenta);
        if(cuentaEntity == null){
            throw new NullPointerException("La Cuenta bancaria no existe");
        }else if(validSaldo(valor)){
            cuentaEntity.retiro(valor);
        }
        return cuentaEntity;
    }
}
