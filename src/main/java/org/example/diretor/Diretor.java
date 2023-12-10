package org.example.diretor;

import org.example.pessoa.Pessoa;

public class Diretor extends Pessoa {



    // Construtor com o nome
    public Diretor(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return "Diretor";
    }
}
