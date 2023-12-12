package org.example.tmdbapi;

import reactor.core.publisher.Mono;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TmdbApi {

    public static Mono<String> obterDadosFilmePeloNome(String nomeFilme) {
        return Mono.defer(() -> {
            try {
                // Substitua este trecho pela lógica real de chamada à API TMDb
                String resultadoDaChamadaApi = chamarApiTmdb(nomeFilme);
                return Mono.just(resultadoDaChamadaApi);
            } catch (IOException | InterruptedException e) {
                return Mono.error(e);
            }
        });
    }

    // Método para chamar a API TMDb (substitua pela lógica real)
    private static String chamarApiTmdb(String nomeFilme) throws IOException, InterruptedException {
        // Substitua este trecho pela lógica real de chamada à API TMDb
        String apiUrl = "https://api.themoviedb.org/3/search/movie?query=" + nomeFilme + "&api_key=SUA_API_KEY";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
