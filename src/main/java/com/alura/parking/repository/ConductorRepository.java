package com.alura.parking.repository;

import com.alura.parking.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {

    Optional<Conductor> findByCpf(String cpf);

    Optional<Conductor> findAllByAddress(String address);

}
