package com.alvorada.tec.hruser.repositories;

import com.alvorada.tec.hruser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Implementando uma consulta query methods
    User findByEmail(String email);
}
