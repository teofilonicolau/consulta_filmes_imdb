package org.example.catalogo;

import org.example.ator.Ator;
import org.example.diretor.Diretor;
import org.example.filme.Filme;
import org.example.filmeParser.FilmeParser;
import org.example.tmdbapi.TmdbApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private final List<Filme> filmes;
    private final List<Ator> atores;
    private final List<Diretor> diretores;

    public Catalogo() {
        this.filmes = new ArrayList<>();
        this.atores = new ArrayList<>();
        this.diretores = new ArrayList<>();
    }

    public void cadastrarAtor(String nomeAtor) {
        Ator ator = new Ator(nomeAtor);
        adicionarAtor(ator);
        System.out.println("Ator cadastrado com sucesso!");
    }

    public void cadastrarDiretor(String nomeDiretor) {
        Diretor diretor = new Diretor(nomeDiretor);
        adicionarDiretor(diretor);
        System.out.println("Diretor cadastrado com sucesso!");
    }

    public void cadastrarFilme(Filme filme) {
        adicionarFilme(filme);
    }

    public void cadastrarFilme(String nomeFilme) {
        Filme filmeExistente = buscarFilmePeloNome(nomeFilme);
        if (filmeExistente != null) {
            System.out.println("Filme já cadastrado.");
        } else {
            try {
                String json = TmdbApi.obterDadosFilmePeloNome(nomeFilme);
                Filme filme = FilmeParser.parseJsonParaFilme(json);
                if (filme != null) {
                    adicionarFilme(filme);
                    System.out.println("Filme cadastrado com sucesso!");
                } else {
                    System.out.println("Erro ao cadastrar o filme.");
                }
            } catch (IOException e) {
                System.out.println("Erro ao obter dados do filme. Verifique o nome e tente novamente.");
            }
        }
    }


    public void associarAtorAoFilme(Ator ator, Filme filme) {
        filme.adicionarAtor(ator);
    }

    public void associarDiretorAoFilme(Diretor diretor, Filme filme) {
        filme.setDiretor(diretor);
    }

    public void associarAtoresEDiretorAoFilme(String nomeFilme, List<String> nomesAtores, String nomeDiretor) {
        Filme filme = buscarFilmePeloNome(nomeFilme);
        if (filme != null) {
            List<Ator> atores = buscarAtoresPorNomes(nomesAtores);
            Diretor diretor = buscarDiretorPeloNome(nomeDiretor);
            if (diretor != null && !atores.isEmpty()) {
                filme.setDiretor(diretor);
                filme.setAtores(atores);
                System.out.println("Atores e diretor associados ao filme com sucesso!");
            } else {
                System.out.println("Diretor ou atores não encontrados.");
            }
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    public List<Filme> pesquisarFilmesPeloNomeIgnoreCase(String nomeFilme) {
        List<Filme> resultados = new ArrayList<>();
        for (Filme filme : filmes) {
            if (filme.getNome().equalsIgnoreCase(nomeFilme)) {
                resultados.add(filme);
            }
        }
        return resultados;
    }

    private void adicionarAtor(Ator ator) {
        atores.add(ator);
    }

    private void adicionarDiretor(Diretor diretor) {
        diretores.add(diretor);
    }

    private void adicionarFilme(Filme filme) {
        filmes.add(filme);
    }

    public Filme buscarFilmePeloNome(String nomeFilme) {
        for (Filme filme : filmes) {
            if (filme.getNome().equalsIgnoreCase(nomeFilme)) {
                return filme;
            }
        }
        return null;
    }

    private List<Ator> buscarAtoresPorNomes(List<String> nomesAtores) {
        List<Ator> atoresEncontrados = new ArrayList<>();
        for (String nome : nomesAtores) {
            Ator ator = buscarAtorPeloNome(nome);
            if (ator != null) {
                atoresEncontrados.add(ator);
            } else {
                System.out.println("Ator não encontrado: " + nome);
            }
        }
        return atoresEncontrados;
    }

    private Ator buscarAtorPeloNome(String nomeAtor) {
        for (Ator ator : atores) {
            if (ator.getNome().equalsIgnoreCase(nomeAtor)) {
                return ator;
            }
        }
        return null;
    }

    private Diretor buscarDiretorPeloNome(String nomeDiretor) {
        for (Diretor diretor : diretores) {
            if (diretor.getNome().equalsIgnoreCase(nomeDiretor)) {
                return diretor;
            }
        }
        return null;
    }
}
