package org.example.aplicacao;

import org.example.apiwebclient.ApiWebClient;
import org.example.catalogo.Catalogo;
import org.example.filme.Filme;
import org.example.filmeParser.FilmeParser;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {
    private static Catalogo catalogo = new Catalogo();
    private static Scanner scanner = new Scanner(System.in);
    private static ApiWebClient apiWebClient = new ApiWebClient(WebClient.builder()); // Correção aqui

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (escolha) {
                case 1:
                    cadastrarFilmeViaAPI();
                    break;
                case 2:
                    cadastrarAtor();
                    break;
                case 3:
                    cadastrarDiretor();
                    break;
                case 4:
                    associarAtoresEDiretorAoFilme();
                    break;
                case 5:
                    pesquisarFilmePorNome();
                    break;
                case 0:
                    System.out.println("Saindo da aplicação. Até logo!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar filme via API");
        System.out.println("2. Cadastrar ator");
        System.out.println("3. Cadastrar diretor");
        System.out.println("4. Associar atores e diretor a um filme");
        System.out.println("5. Pesquisar filme por nome");
        System.out.println("0. Sair");
    }

    private static void cadastrarFilmeViaAPI() {
        System.out.println("Cadastro de Filme via API:");
        System.out.print("Informe o nome do filme: ");
        String nomeFilme = scanner.nextLine();

        try {
            apiWebClient.consultarApiTmdbFilme(nomeFilme)
                    .subscribe(
                            json -> {
                                // Verificar se a resposta da API foi bem-sucedida
                                if (json.contains("error_message")) {
                                    System.out.println("Erro ao cadastrar o filme via API: " + json);
                                } else {
                                    Filme filme = FilmeParser.parseJsonParaFilme(json);
                                    catalogo.cadastrarFilme(filme);
                                    System.out.println("Filme cadastrado com sucesso!");
                                }
                            },
                            e -> System.out.println("Erro ao cadastrar o filme via API: " + e.getMessage())
                    );
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar o filme via API: " + e.getMessage());
        }
    }

    private static void cadastrarAtor() {
        System.out.println("Cadastro de Ator:");
        System.out.print("Informe o nome do ator: ");
        String nomeAtor = scanner.nextLine();
        catalogo.cadastrarAtor(nomeAtor);
        System.out.println("Ator cadastrado com sucesso!");
    }

    private static void cadastrarDiretor() {
        System.out.println("Cadastro de Diretor:");
        System.out.print("Informe o nome do diretor: ");
        String nomeDiretor = scanner.nextLine();
        catalogo.cadastrarDiretor(nomeDiretor);
        System.out.println("Diretor cadastrado com sucesso!");
    }

    private static void associarAtoresEDiretorAoFilme() {
        System.out.println("Associar Atores e Diretor a um Filme:");

        // Solicitar ao usuário o nome do filme
        System.out.print("Informe o nome do filme: ");
        String nomeFilmeAssociacao = scanner.nextLine();

        // Verificar se o filme existe no catálogo
        Filme filmeAssociacao = catalogo.buscarFilmePeloNome(nomeFilmeAssociacao);
        if (filmeAssociacao != null) {
            // Solicitar ao usuário os nomes dos atores (pode ser uma lista separada por vírgulas)
            System.out.print("Informe os nomes dos atores (separados por vírgulas): ");
            String nomesAtores = scanner.nextLine();
            List<String> nomesAtoresList = Arrays.asList(nomesAtores.split("\\s*,\\s*"));

            // Solicitar ao usuário o nome do diretor
            System.out.print("Informe o nome do diretor: ");
            String nomeDiretorAssociacao = scanner.nextLine();

            // Realizar a associação usando os métodos do catálogo
            catalogo.associarAtoresEDiretorAoFilme(nomeFilmeAssociacao, nomesAtoresList, nomeDiretorAssociacao);
            System.out.println("Atores e diretor associados ao filme com sucesso!");
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    private static void pesquisarFilmePorNome() {
        System.out.println("Pesquisa de Filme por Nome:");
        System.out.print("Informe o nome do filme: ");
        String nomeFilmePesquisa = scanner.nextLine();
        List<Filme> resultados = catalogo.pesquisarFilmesPeloNomeIgnoreCase(nomeFilmePesquisa);
        if (!resultados.isEmpty()) {
            System.out.println("Resultados da pesquisa:");
            for (Filme filme : resultados) {
                System.out.println(filme);
            }
        } else {
            System.out.println("Nenhum filme encontrado com o nome informado.");
        }
    }
}
