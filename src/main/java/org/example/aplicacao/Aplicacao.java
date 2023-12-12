package org.example.aplicacao;

import org.example.apiwebclient.ApiWebClient;
import org.example.catalogo.Catalogo;
import org.example.filme.Filme;
import org.example.repository.AtorRepository;
import org.example.repository.DiretorRepository;
import org.example.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan("org.example")
public class Aplicacao implements CommandLineRunner {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AtorRepository atorRepository;

    @Autowired
    private DiretorRepository diretorRepository;

    @Autowired
    private ApiWebClient apiWebClient;

    public static void main(String[] args) {
        SpringApplication.run(Aplicacao.class, args);
    }

    @Override
    public void run(String... args) {
        iniciar();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar filme via API");
        System.out.println("2. Cadastrar ator");
        System.out.println("3. Cadastrar diretor");
        System.out.println("4. Associar atores e diretor a um filme");
        System.out.println("5. Pesquisar filme por nome");
        System.out.println("0. Sair");

        int escolha = scanner.nextInt();

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
                associarAtoresDiretorFilme();
                break;
            case 5:
                pesquisarFilmePorNome();
                break;
            case 0:
                System.out.println("Saindo da aplicação. Até logo!");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                iniciar();
        }
    }

    private void cadastrarFilmeViaAPI() {
        // Lógica para cadastrar filme via API
        System.out.println("Cadastro de Filme via API:");
        System.out.print("Informe o nome do filme: ");
        String nomeFilme = scanner.next();
        // Implemente a lógica de cadastro do filme via API
        System.out.println("Filme cadastrado com sucesso!");
        iniciar();
    }

    private void cadastrarAtor() {
        // Lógica para cadastrar ator
        System.out.println("Cadastro de Ator:");
        System.out.print("Informe o nome do ator: ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String nomeAtor = scanner.nextLine(); // Usar nextLine() para ler toda a linha

        // Implemente a lógica de cadastro do ator
        Catalogo catalogo = new Catalogo(filmeRepository, atorRepository, diretorRepository);
        catalogo.cadastrarAtor(nomeAtor);

        System.out.println("Ator cadastrado com sucesso!");
        iniciar();
    }

    private void cadastrarDiretor() {
        // Lógica para cadastrar diretor
        System.out.println("Cadastro de Diretor:");
        System.out.print("Informe o nome do diretor: ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String nomeDiretor = scanner.nextLine(); // Usar nextLine() para ler toda a linha

        // Implemente a lógica de cadastro do diretor
        Catalogo catalogo = new Catalogo(filmeRepository, atorRepository, diretorRepository);
        catalogo.cadastrarDiretor(nomeDiretor);

        System.out.println("Diretor cadastrado com sucesso!");
        iniciar();
    }

    private void associarAtoresDiretorFilme() {
        // Lógica para associar atores e diretor a um filme
        System.out.println("Associação de Atores e Diretor a um Filme:");
        System.out.print("Informe o nome do filme: ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String nomeFilme = scanner.nextLine(); // Usar nextLine() para ler toda a linha

        System.out.print("Informe os nomes dos atores (separados por vírgula): ");
        String nomesAtores = scanner.nextLine();

        System.out.print("Informe o nome do diretor: ");
        String nomeDiretor = scanner.nextLine();

        Catalogo catalogo = new Catalogo(filmeRepository, atorRepository, diretorRepository);
        catalogo.associarAtoresEDiretorAoFilme(nomeFilme, Arrays.asList(nomesAtores.split(",")), nomeDiretor);

        System.out.println("Atores e diretor associados ao filme com sucesso!");
        iniciar();
    }

    private void pesquisarFilmePorNome() {
        // Lógica para pesquisar filme por nome
        System.out.println("Pesquisa de Filme por Nome:");
        System.out.print("Informe o nome do filme: ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String nomeFilme = scanner.nextLine(); // Usar nextLine() para ler toda a linha

        Catalogo catalogo = new Catalogo(filmeRepository, atorRepository, diretorRepository);
        List<Filme> resultados = catalogo.pesquisarFilmesPeloNomeIgnoreCase(nomeFilme);

        if (!resultados.isEmpty()) {
            for (Filme filme : resultados) {
                System.out.println(filme);
            }
        } else {
            System.out.println("Nenhum filme encontrado com o nome informado.");
        }
        iniciar();
    }
}
