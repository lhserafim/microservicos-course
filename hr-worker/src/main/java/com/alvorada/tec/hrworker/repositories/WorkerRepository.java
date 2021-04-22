package com.alvorada.tec.hrworker.repositories;

import com.alvorada.tec.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
