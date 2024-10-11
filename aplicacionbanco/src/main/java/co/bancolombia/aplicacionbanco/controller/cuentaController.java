package co.bancolombia.aplicacionbanco.controller;

import co.bancolombia.aplicacionbanco.service.cuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigDecimal;

@RestController
@RequestMapping("/numCuenta")
public class cuentaController {

    private final cuentaService cuentaservice;


    public cuentaController(cuentaService cuentaservice){
        this.cuentaservice = cuentaservice;
    }

   // @Autowired
    //private cuentaService cuentaservice;

    @GetMapping("/saldo/{numCuenta}")
    public ResponseEntity<BigDecimal> obtenerSaldo(@PathVariable(name = "numCuenta") String numCuenta) {
        if(cuentaservice.obtenerSaldo(numCuenta) == null)
            throw new NullPointerException("Debe enviar el nuemro de la cuenta");
        return ResponseEntity.ok().body(cuentaservice.obtenerSaldo(numCuenta));
    }


    @PostMapping("/deposito/{numCuenta}")
    public ResponseEntity<String> depositar(@PathVariable(name = "numCuenta") String numCuenta,@RequestParam(name = "monto") BigDecimal monto) {
        if ((numCuenta == null) || (monto == null) )
            throw new NullPointerException("El numero de cuenta o el monto no debe ser nulo");
        return ResponseEntity.ok().body(cuentaservice.depositar(numCuenta, monto));
    }


    @PostMapping("/retirar/{numCuenta}")
    public ResponseEntity<String> retirar(@PathVariable String numCuenta, @RequestParam(name = "monto") BigDecimal monto) {
        if ( (numCuenta == null) || (monto == null) )
            throw new NullPointerException("El numero de cuenta o el monto no debe ser nulo");
        return ResponseEntity.ok().body(cuentaservice.retirar(numCuenta,monto));

    }
}




