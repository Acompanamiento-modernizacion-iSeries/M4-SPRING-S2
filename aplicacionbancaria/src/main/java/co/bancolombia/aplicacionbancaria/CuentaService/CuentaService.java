package co.bancolombia.aplicacionbancaria.CuentaService;

import co.bancolombia.aplicacionbancaria.Cuenta.Cuenta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaService {
    private List<Cuenta> cuentas = new ArrayList<>();

    public CuentaService() {
        cuentas.add(new Cuenta("123", new BigDecimal("1000.00")));
        cuentas.add(new Cuenta("456", new BigDecimal("2000.00")));
    }

    public BigDecimal getSaldo(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta.getSaldo();
            }
        }
        throw new IllegalArgumentException("Cuenta no encontrada.");
    }

    public void depositar(String numeroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("No se permiten depósitos con valores negativos.");
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                cuenta.setSaldo(cuenta.getSaldo().add(monto));
                return;
            }
        }
        throw new IllegalArgumentException("Cuenta no encontrada.");
    }

    public void retirar(String numeroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("No se permiten retiros con valores negativos.");
        }
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                if (cuenta.getSaldo().compareTo(monto) < 0) {
                    throw new IllegalArgumentException("No se permiten saldos negativos.");
                }
                cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
                return;
            }
        }
        throw new IllegalArgumentException("Cuenta no encontrada.");
    }
}