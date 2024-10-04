package co.com.bancolombia.aplicacionbancaria.service;

import co.com.bancolombia.aplicacionbancaria.model.Cuenta;
import co.com.bancolombia.aplicacionbancaria.repository.CuentasDB;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaService {

    private final CuentasDB CUENTAS_DB;

    public CuentaService(CuentasDB cuentasDb) {
        this.CUENTAS_DB = cuentasDb;
    }

    public BigDecimal consultarSaldo(String cuenta) {
        return buscarCuenta(cuenta).saldo();
    }

    public BigDecimal deposito(String numCuenta, String monto) {
        if (!monto.matches("[-+]?\\d*\\.?\\d+"))
            throw new NumberFormatException("Ingrese un monto válido.");

        BigDecimal vlrTransacc = new BigDecimal(monto);

        if (vlrTransacc.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El monto debe ser mayor a cero!");

        BigDecimal nuevoSaldo = buscarCuenta(numCuenta).saldo().add(vlrTransacc);
        buscarCuenta(numCuenta).actualizarSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    public BigDecimal retiro(String numCuenta, String monto) {
        if (!monto.matches("[-+]?\\d*\\.?\\d+"))
            throw new NumberFormatException("Ingrese un monto válido.");

        BigDecimal vlrTransacc = new BigDecimal(monto);

        if (vlrTransacc.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("El monto debe ser mayor a cero!");
        if (vlrTransacc.compareTo(buscarCuenta(numCuenta).saldo()) > 0)
            throw new IllegalArgumentException("No tiene fondos suficientes!");

        BigDecimal nuevoSaldo = buscarCuenta(numCuenta).saldo().subtract(vlrTransacc);
        buscarCuenta(numCuenta).actualizarSaldo(nuevoSaldo);
        return nuevoSaldo;
    }

    public Cuenta buscarCuenta(String numCuenta) {
        for (Cuenta cuenta : CUENTAS_DB.listaCuentas()) {
            if (cuenta.numeroCuenta().equals(numCuenta)) {
                return cuenta;
            }
        }
        throw new NullPointerException("No existe una cuenta con el número proporcionado");
    }
}
