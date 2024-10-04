package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.config.MontoInvalidoException;
import com.bancolombia.aplicacionbancaria.config.SaldoInsuficienteException;
import com.bancolombia.aplicacionbancaria.entity.Cuenta;
import com.bancolombia.aplicacionbancaria.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public BigDecimal consultarSaldo(Long numeroCuenta) {
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(numeroCuenta);
        if (!cuentaOpt.isPresent()) {
            throw new RuntimeException( "Cuenta Invalida o inexistente");
        }
        return cuentaOpt.get().getSaldo();
    }

    public boolean depositar(Long numeroCuenta, BigDecimal cantidad) {
        if (cantidad == null || cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontoInvalidoException();
        }

        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(numeroCuenta);
        if (!cuentaOpt.isPresent()) {
            throw new RuntimeException( "Cuenta Invalida o inexistente");
        }

        Cuenta cuenta = cuentaOpt.get();
        cuenta.setSaldo(cuenta.getSaldo().add(cantidad));
        cuentaRepository.save(cuenta);
        return true;
    }

    public boolean retirar(Long numeroCuenta, BigDecimal cantidad) {
        if (cantidad == null || cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontoInvalidoException();
        }

        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(numeroCuenta);
        if (!cuentaOpt.isPresent()) {
            throw new RuntimeException( "Cuenta Invalida o inexistente");
        }

        Cuenta cuenta = cuentaOpt.get();
        if (cuenta.getSaldo().compareTo(cantidad) < 0) {
            throw new SaldoInsuficienteException();
        }

        cuenta.setSaldo(cuenta.getSaldo().subtract(cantidad));
        cuentaRepository.save(cuenta);
        return true;
    }
}
