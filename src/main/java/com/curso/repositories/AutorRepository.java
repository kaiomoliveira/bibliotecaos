package com.curso.repositories;

import com.curso.domains.Autor;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Entity
public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Optional<Autor> findBydocumentoPessoal(String documentoPessoal);
}
