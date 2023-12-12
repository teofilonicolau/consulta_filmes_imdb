package org.example.aplicacao;

import org.example.apiwebclient.ApiWebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ApiWebClient appApiWebClient() {
        return new ApiWebClient(); // Substitua isso pela inicialização real do cliente
    }

    // Remova a definição do bean 'catalogo', pois já é definido como um componente do Spring.
    // @Bean
    // public Catalogo catalogo(FilmeRepository filmeRepository, AtorRepository atorRepository, DiretorRepository diretorRepository) {
    //     return new Catalogo(filmeRepository, atorRepository, diretorRepository);
    // }
}
