package org.example.filme;

import org.example.ator.Ator;
import org.example.diretor.Diretor;

import java.util.List;

public class Filme {
    private String nome;
    private int ano;
    private double orcamento;
    private String descricao;
    private Diretor diretor;
    private List<Ator> atores;

    // Construtor
    public Filme(String nome, int ano, double orcamento, String descricao, Diretor diretor, List<Ator> atores) {
        this.nome = nome;
        this.ano = ano;
        this.orcamento = orcamento;
        this.descricao = descricao;
        this.diretor = diretor;
        this.atores = atores;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    // Método para adicionar ator à lista de atores
    public void adicionarAtor(Ator ator) {
        this.atores.add(ator);
    }

    // Método toString para exibir informações do filme
    @Override
    public String toString() {
        return "Filme{" +
                "nome='" + nome + '\'' +
                ", ano=" + ano +
                ", orcamento=" + orcamento +
                ", descricao='" + descricao + '\'' +
                ", diretor=" + diretor +
                ", atores=" + atores +
                '}';
    }
}
