package co.bancolombia.aplicacionbancaria.controllers;

import java.math.BigDecimal;
        import java.util.List;

        import co.bancolombia.aplicacionbancaria.services.CuentaService;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import co.bancolombia.aplicacionbancaria.models.Cuenta;

        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private CuentaService cuentaService;
    public CuentaController(CuentaService cuentaService){
        this.cuentaService = cuentaService;
    }

    @GetMapping("/listar_cuentas")
    public List<Cuenta> listarCuentas() {
        List<Cuenta> listacuentas = cuentaService.listarCuentas();
        return listacuentas;
    }

    @GetMapping("/saldo/{idCuenta}")
    public String saldo(@PathVariable("idCuenta") String idCuenta) {
        Cuenta cuenta = cuentaService.BuscarIdCuenta(idCuenta);
        return "Cuenta : " + idCuenta + " \nSaldo  : " + cuenta.getSaldo();
    }

    @PostMapping("/deposito/{idCuenta}/{monto}")
    public String deposito(@PathVariable("idCuenta") String idCuenta, @PathVariable("monto") BigDecimal monto) {
        BigDecimal nuevoSaldo = cuentaService.deposito(idCuenta, monto);
        return "DEPOSITO EXITOSO!!! \n\nCuenta : " + idCuenta + " \nSaldo  : " + nuevoSaldo;
    }

    @PostMapping("/retiro/{idCuenta}/{monto}")
    public String retiro(@PathVariable("idCuenta") String idCuenta, @PathVariable("monto") BigDecimal monto) {
        BigDecimal nuevoSaldo = cuentaService.retiro(idCuenta, monto);
        return "RETIRO EXITOSO!!! \n\nCuenta : " + idCuenta + " \nSaldo  : " + nuevoSaldo;
    }
}