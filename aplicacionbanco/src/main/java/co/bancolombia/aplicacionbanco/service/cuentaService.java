package co.bancolombia.aplicacionbanco.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import co.bancolombia.aplicacionbanco.model.Cuenta;

import org.springframework.stereotype.Service;

@Service
public class cuentaService {

    private Map<String, Cuenta> cuentas = new HashMap<>();

    public cuentaService() {
        cuentas.put("cta123", new Cuenta("cta123"));
        cuentas.put("cta456", new Cuenta("cta456"));
    }


    public BigDecimal obtenerSaldo(String numCuenta) {
        Cuenta cuenta = cuentas.get(numCuenta);
        return cuenta.getSaldo();
    }

    public String depositar(String numCuenta, BigDecimal monto) {
        Cuenta cuenta = cuentas.get(numCuenta);
        if (cuenta.getNumeroCuenta().isEmpty())
            throw new NullPointerException("El numero de cuenta no existe");
        if (monto.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
        cuenta.depositar(monto);
        return "Deposito realizado con exito: " + cuenta.getSaldo();

        }


    public String retirar(String numCuenta, BigDecimal monto) {
        Cuenta cuenta = cuentas.get(numCuenta);
        if (cuenta.getNumeroCuenta().isEmpty())
            throw new NullPointerException("El numero de cuenta no existe");
        if (monto.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
        if (cuenta.getSaldo().compareTo(monto) < 0)
            throw  new IllegalArgumentException("Fondos insuficientes");
        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
        return "Retiro realizado con exito: " + cuenta.getSaldo();

    }
}


