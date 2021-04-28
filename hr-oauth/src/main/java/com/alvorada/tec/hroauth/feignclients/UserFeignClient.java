package com.alvorada.tec.hroauth.feignclients;

import com.alvorada.tec.hroauth.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component // Para ser gerenciado pelo Spring
@FeignClient(name = "hr-user",path = "/users") // nome do MS que eu vou me comunicar e seu path
public interface UserFeignClient {

    // Vou copiar p/ cá o recurso que eu quero acessar do projeto hr-user
    @GetMapping(value = "/search")
    ResponseEntity<User> findByEmail(@RequestParam String email);
}
