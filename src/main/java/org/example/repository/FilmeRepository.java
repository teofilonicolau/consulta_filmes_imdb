package org.example.repository;

import org.example.filme.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByNomeIgnoreCase(String nome);
}
