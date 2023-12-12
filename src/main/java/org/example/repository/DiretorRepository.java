package org.example.repository;

import org.example.diretor.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {
    Diretor findByNome(String nome);
}