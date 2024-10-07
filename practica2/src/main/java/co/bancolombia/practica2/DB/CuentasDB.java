package co.bancolombia.practica2.DB;

import org.springframework.stereotype.Repository;
import co.bancolombia.practica2.cuenta.Cuenta;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class CuentasDB {


    public static List<Cuenta> cuentas = List.of(
            new Cuenta("1", new BigDecimal("1000")), // Cuenta 1 con saldo 1000
            new Cuenta("2", new BigDecimal("2000")), // Cuenta 2 con saldo 2000
            new Cuenta("3", new BigDecimal("3000")), // Cuenta 3 con saldo 3000
            new Cuenta("4", new BigDecimal("4000")), // Cuenta 4 con saldo 4000
            new Cuenta("5", new BigDecimal("5000"))  // Cuenta 5 con saldo 5000
    );
}
