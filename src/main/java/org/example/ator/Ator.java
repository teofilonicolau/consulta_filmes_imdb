package org.example.ator;

import org.example.pessoa.Pessoa;


public class Ator extends Pessoa {

    // Construtor apenas com o nome
    public Ator(String nome) {
        super(nome, 0, "", ""); // Valores padrão para outros atributos
    }

    @Override
    public String getTipo() {
        return "Ator";
    }
}
