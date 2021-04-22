package com.alvorada.tec.hrpayroll.feignclients;

import com.alvorada.tec.hrpayroll.entities.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component // Para ser escaneado pelo spring e poder injetá-lo
@FeignClient(name = "hr-worker", url = "localhost:8001", path = "/workers") // Declarando os parâmetros
public interface WorkerFeignClient {

    // Vou copiar p/ cá o recurso que eu quero acessar do projeto hr-worker
    @GetMapping(value = "/{id}")
    ResponseEntity<Worker> findById(@PathVariable Long id);

}
