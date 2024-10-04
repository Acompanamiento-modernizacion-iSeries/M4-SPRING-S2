package co.com.bancolombia.aplicacionbancaria.repository;

import co.com.bancolombia.aplicacionbancaria.model.Cuenta;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CuentasDB {

    private final List<Cuenta> LISTA_CUENTAS = List.of(
            new Cuenta("1234567890", new BigDecimal(21000)),
            new Cuenta("1122334455", new BigDecimal(12000)),
            new Cuenta("1112223334", new BigDecimal(25000))
    );

    public List<Cuenta> listaCuentas() {
        return LISTA_CUENTAS;
    }
}
