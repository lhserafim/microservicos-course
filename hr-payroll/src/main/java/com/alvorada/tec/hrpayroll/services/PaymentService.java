package com.alvorada.tec.hrpayroll.services;

import com.alvorada.tec.hrpayroll.entities.Payment;
import com.alvorada.tec.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    // Fazendo a interpolação do valor da chave criada no arquivo application.properties
    @Value("${hr-worker.host}")
    private String workerHost; // recebe o valor interpolado

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(long workerId, int days) {
        // Fazendo o mapa de parâmetros. Usamos a Interface Map que recebe uma coleção de chaves/valores. Não instanciamos interfaces e por isso
        // fazemos a instancia da classe concreta, que é o HashMap<>
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", String.valueOf(workerId)); // Adicionando um valor ao meu Map

        // Para chamar a API hr-worker, precisei criar a classe e chamar o método getForObject()
        // getForObject() recebe 3 parâmetros.
        // URL
        // Tipo do objeto que estou buscando. Neste caso o tipo está declarado na classe Worker
        // Parâmetros
        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables); // {id} é a chave criada no Map<K,V>
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
