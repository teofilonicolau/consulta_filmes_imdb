package org.example.apiwebclient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
public class ApiWebClient {

    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/search/movie";
    private static final String API_KEY = "812a3694d28cb7e9840d5842e9126a23";

    private final OkHttpClient httpClient = new OkHttpClient();

    public Mono<String> obterDadosFilmePeloNome(String nomeFilme) {
        String url = MOVIE_BASE_URL + "?query=" + nomeFilme + "&api_key=" + API_KEY;
        Request request = new Request.Builder().url(url).build();

        return Mono.fromCallable(() -> {
            try (Response response = httpClient.newCall(request).execute()) {
                return response.body().string();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao consultar a API", e);
            }
        });
    }
}
