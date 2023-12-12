package org.example.filme;

import org.example.ator.Ator;
import org.example.diretor.Diretor;

import java.util.List;

public class Filme {
    private String nome;
    private Diretor diretor;
    private List<Ator> atores;

    public Filme(String nome, Diretor diretor, List<Ator> atores) {
        this.nome = nome;
        this.diretor = diretor;
        this.atores = atores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public void adicionarAtor(Ator ator) {
        this.atores.add(ator);
    }

    @Override
    public String toString() {
        return "Filme{" +
                "nome='" + nome + '\'' +
                ", diretor=" + diretor +
                ", atores=" + atores +
                '}';
    }
}
