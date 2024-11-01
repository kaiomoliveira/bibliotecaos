package com.curso.repositories;

import com.curso.domains.Editora;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Entity
public interface EditoraRepository extends JpaRepository<Editora, Integer> {
    Optional<Editora> findByCnpj(String cnpj);
}