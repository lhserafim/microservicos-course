package com.alvorada.tec.hrpayroll.resources;

import com.alvorada.tec.hrpayroll.entities.Payment;
import com.alvorada.tec.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResources {

    @Autowired
    private PaymentService paymentService;

    @HystrixCommand(fallbackMethod = "getPaymentAlternative") // Nome do método alternativo que será chamado em caso de exceção
    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId,
                                              @PathVariable Integer days) {
        return ResponseEntity.ok(paymentService.getPayment(workerId, days));
    }

    // Método alternativo p/ ser chamado pelo Hystrix. Removido o @PathVariable
    public ResponseEntity<Payment> getPaymentAlternative(Long workerId, Integer days) {
        // A implementação abaixo é uma regra de negócio
        Payment payment = new Payment("Brann", 400.0, days);
        return ResponseEntity.ok(payment);
    }


}
