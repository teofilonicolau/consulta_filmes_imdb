package org.example.apiwebclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ApiWebClient {
    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/search/movie";
    private static final String PERSON_BASE_URL = "https://api.themoviedb.org/3/search/person";
    private static final String ADD_MOVIE_URL = "https://api.themoviedb.org/3/list/list_id/add_item";
    private static final String API_KEY = "812a3694d28cb7e9840d5842e9126a23";

    private final WebClient movieWebClient;
    private final WebClient personWebClient;
    private final WebClient addMovieWebClient;

    public ApiWebClient(WebClient.Builder webClientBuilder) {
        this.movieWebClient = createWebClient(webClientBuilder, MOVIE_BASE_URL);
        this.personWebClient = createWebClient(webClientBuilder, PERSON_BASE_URL);
        this.addMovieWebClient = createWebClient(webClientBuilder, ADD_MOVIE_URL);
    }

    private WebClient createWebClient(WebClient.Builder webClientBuilder, String baseUrl) {
        return webClientBuilder.baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MTJhMzY5NGQyOGNiN2U9ODQwZDU4NDJlOTEyNmEyMyIsInN1I6IjY1NzBhZDNiMGQxZTdmMDE0ZDViM2UwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.l5Q_bercgLHcPxdsD41qHtiQ3eONxkJxK9cf0CFoLf0")
                .build();
    }

    public Mono<String> consultarApiTmdbFilme(String nomeFilme) {
        return movieWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", nomeFilme)
                        .queryParam("api_key", API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> consultarApiTmdbPessoa(String nomePessoa) {
        return personWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", nomePessoa)
                        .queryParam("api_key", API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> adicionarFilme() {
        return addMovieWebClient.post()
                .uri(uriBuilder -> uriBuilder.build())
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .bodyValue("{" +
                        "\"title\": \"Seu Título\"," +
                        "\"overview\": \"Sua Descrição\"," +
                        // Outros campos necessários...
                        "}")
                .retrieve()
                .bodyToMono(String.class);
    }
}
