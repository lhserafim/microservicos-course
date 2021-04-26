package com.alvorada.tec.hrworker.resources;

import com.alvorada.tec.hrworker.entities.Worker;
import com.alvorada.tec.hrworker.repositories.WorkerRepository;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    // Implementação para poder fazer testes de balanceamento de carga

    // Instanciar o logger para imprimir coisas no log
    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);
    @Autowired
    private Environment environment;
    // Este objeto (Enviroment) tem várias informações do contexto da aplicação.

    @Autowired // Não foi necessário criar um construtor para fazer a injeção de dependência do repository na resource
    private WorkerRepository workerRepository;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        return ResponseEntity.ok(workerRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {

        // CÓDIGO P/ SIMULAR UM TIMEOUT
        // por padrão o balanceamento de carga do ribbon tem 1s
 		/*
        try {
 			Thread.sleep(3000L);
 		} catch (InterruptedException e) {
 			e.printStackTrace();
 		}
 		*/

        // Recuperando a porta do enviroment e logando no console
        logger.info("PORT = " + environment.getProperty("local.server.port"));

        return ResponseEntity.ok(workerRepository.findById(id).get()); // Usando o .get() ao invés do .orElseThrow()
    }

}
