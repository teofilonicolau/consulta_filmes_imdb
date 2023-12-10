package org.example.filmeParser;

import org.example.ator.Ator;
import org.example.diretor.Diretor;
import org.example.filme.Filme;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FilmeParser {

    public static Filme parseJsonParaFilme(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            // Adicionando diretor
            Diretor diretor = null;
            JSONObject directorObject = jsonObject.optJSONObject("director");
            if (directorObject != null) {
                diretor = new Diretor(directorObject.optString("name", ""));
            }

            // Extraindo outras informações do filme
            String titulo = jsonObject.optString("title", "");
            int ano = jsonObject.optInt("year", 0);
            double orcamento = jsonObject.optDouble("budget", 0.0);
            String descricao = jsonObject.optString("description", "");

            // Extraindo a lista de atores
            List<Ator> atores = new ArrayList<>();
            JSONArray castArray = jsonObject.optJSONArray("cast");
            if (castArray != null) {
                for (int i = 0; i < castArray.length(); i++) {
                    JSONObject actorObject = castArray.optJSONObject(i);
                    if (actorObject != null) {
                        String nomeAtor = actorObject.optString("name", "");
                        Ator ator = new Ator(nomeAtor);
                        atores.add(ator);
                    }
                }
            }

            // Criando e retornando um objeto Filme
            return new Filme(titulo, ano, orcamento, descricao, diretor, atores);
        } catch (JSONException e) {
            e.printStackTrace();
            return null; // Trate o erro conforme necessário
        }
    }
}