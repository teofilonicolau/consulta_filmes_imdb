package org.example.filmeParser;

import org.example.ator.Ator;
import org.example.diretor.Diretor;
import org.example.filme.Filme;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilmeParser {

    public static Filme parseJsonParaFilme(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            // Corrigir chamada do construtor de Filme
            String title = jsonObject.getString("title");
            String releaseDate = jsonObject.getString("release_date");
            String overview = jsonObject.getString("overview");

            // Ajustar chamada do construtor para incluir o diretor
            String nomeDiretor = jsonObject.getString("director");
            Diretor diretor = new Diretor(nomeDiretor);

            // Ajustar chamada do construtor para incluir os atores
            List<String> nomesAtores = new ArrayList<>();
            JSONArray castArray = jsonObject.getJSONArray("cast");
            for (int i = 0; i < castArray.length(); i++) {
                String nomeAtor = castArray.getString(i);
                nomesAtores.add(nomeAtor);
            }

            // Criar a lista de objetos Ator
            List<Ator> atores = nomesAtores.stream().map(Ator::new).collect(Collectors.toList());

            return new Filme(title, diretor, atores);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer o parsing do JSON para Filme", e);
        }
    }

}