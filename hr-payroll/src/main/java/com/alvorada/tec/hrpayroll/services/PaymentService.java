package com.alvorada.tec.hrpayroll.services;

import com.alvorada.tec.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment getPayment(long workerId, int days) {
        // Por enquanto vamos instanciar um pagamento mockado
        return new Payment("Bob", 200.0, days);
    }
}
