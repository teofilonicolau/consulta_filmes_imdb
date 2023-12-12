package org.example.repository;

import org.example.ator.Ator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtorRepository extends JpaRepository<Ator, Long> {
    List<Ator> findByNomeIn(List<String> nomes);
}