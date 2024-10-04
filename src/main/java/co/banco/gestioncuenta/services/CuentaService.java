package co.banco.gestioncuenta.services;

import co.banco.gestioncuenta.db.CuentaRepository;
import co.banco.gestioncuenta.model.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaService {

    //@Autowired
    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public BigDecimal obtenerSaldo(String numeroCuenta) {
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        return cuenta.getSaldo();
    }

    public BigDecimal depositar(String numeroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        cuenta.depositar(monto);
        return cuenta.getSaldo();
    }

    public BigDecimal retirar(String numeroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        if (!cuenta.retirar(monto)) {
            throw new IllegalStateException("Saldo insuficiente.");
        }
        return cuenta.getSaldo();
    }

    private Cuenta obtenerCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no encontrada.");
        }
        return cuenta;
    }
}
