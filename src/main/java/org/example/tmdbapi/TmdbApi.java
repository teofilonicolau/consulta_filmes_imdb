package org.example.tmdbapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TmdbApi {

    private static final String API_KEY = "812a3694d28cb7e9840d5842e9126a23";
    private static final String BASE_URL = "https://api.themoviedb.org/3/search/movie";

    // Altere a assinatura do método obterDadosFilmePeloNome em TmdbApi
    public static String obterDadosFilmePeloNome(String nomeFilme) throws IOException {
        try {
            String nomeEncoded = URLEncoder.encode(nomeFilme, StandardCharsets.UTF_8);
            String url = BASE_URL + "?api_key=" + API_KEY + "&query=" + nomeEncoded;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e; // ou lançar uma exceção personalizada, dependendo do contexto
        }
    }
}
