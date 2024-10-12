package co.bancolombia.aplicacionbancaria.services;

        import co.bancolombia.aplicacionbancaria.models.Cuenta;

        import co.bancolombia.aplicacionbancaria.repository.CuentasRepository;
        import org.springframework.stereotype.Service;

        import java.math.BigDecimal;
        import java.util.List;
        import java.util.NoSuchElementException;

@Service
public class CuentaService {

    private CuentasRepository cuentaRepository ;
    public CuentaService(CuentasRepository cuentas){
        this.cuentaRepository = cuentas;
    }

    public List<Cuenta> listarCuentas(){

        if (cuentaRepository.listarCuentas().isEmpty()){
            throw new NoSuchElementException("No existen cuentas creadas");
        }
        return cuentaRepository.listarCuentas();
    }

    public Cuenta BuscarIdCuenta(String idCuenta){
        Cuenta cuenta = cuentaRepository.buscarIdCuenta(idCuenta);
        if(cuenta == null || cuenta.getCuenta().isEmpty()){
            throw new NullPointerException("Cuenta: " + idCuenta + ", no existe");
        }
        return cuenta;
    }

    public BigDecimal deposito(String idCuenta, BigDecimal monto){
        Cuenta cuenta  = BuscarIdCuenta(idCuenta);
        if(cuenta == null || cuenta.getCuenta().isEmpty()){
            throw new NullPointerException("Cuenta: " + idCuenta + ", no existe");
        }
        if(monto.compareTo(BigDecimal.ZERO)<= 0) {
            throw new IllegalArgumentException("El valor ingresado no es valido.");
        }
        cuenta.setSaldo(cuenta.getSaldo().add(monto));
        return cuenta.getSaldo();
    }

    public BigDecimal retiro(String idCuenta, BigDecimal monto){
        Cuenta cuenta  = BuscarIdCuenta(idCuenta);
        if(cuenta == null || cuenta.getCuenta().isEmpty()){
            throw new NullPointerException("Cuenta: " + idCuenta + ", no existe");
        }

        if (monto.compareTo(BigDecimal.ZERO)<= 0) {
            throw new IllegalArgumentException("El valor ingresado no es valido.");
        }

        if(cuenta.getSaldo().compareTo(monto)< 0){
            throw new IllegalArgumentException("saldo insuficiente");
        }
        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));

        return cuenta.getSaldo();
    }

}
