package com.alvorada.tec.hrpayroll.services;

import com.alvorada.tec.hrpayroll.entities.Payment;
import com.alvorada.tec.hrpayroll.entities.Worker;
import com.alvorada.tec.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, int days) {
        // Acesso a interface feign, que foi injetada e chamo o método que corresponde a API que eu quero acessar
        // lá no outro projeto (API).
        // Como o meu endpoint de hr-worker retorna um ResponseEntity, preciso do .getBody() para recuperar a "Entidade"
        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days); // ideal seria tratar as exceções pois pode retornar nulo!
    }

}
