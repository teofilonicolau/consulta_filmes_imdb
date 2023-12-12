package org.example.catalogo;

import org.example.ator.Ator;
import org.example.diretor.Diretor;
import org.example.filme.Filme;
import org.example.repository.AtorRepository;
import org.example.repository.DiretorRepository;
import org.example.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class Catalogo {

    private final FilmeRepository filmeRepository;
    private final AtorRepository atorRepository;
    private final DiretorRepository diretorRepository;

    @Autowired
    public Catalogo(FilmeRepository filmeRepository, AtorRepository atorRepository, DiretorRepository diretorRepository) {
        this.filmeRepository = filmeRepository;
        this.atorRepository = atorRepository;
        this.diretorRepository = diretorRepository;
    }

    public void cadastrarFilme(Filme filme) {
        filmeRepository.save(filme);
    }

    public List<Filme> pesquisarFilmesPeloNomeIgnoreCase(String nomeFilme) {
        return filmeRepository.findByNomeIgnoreCase(nomeFilme);
    }

    public void cadastrarAtor(String nomeAtor) {
        Ator ator = new Ator(nomeAtor);
        atorRepository.save(ator);
        System.out.println("Ator cadastrado com sucesso!");
    }

    public void cadastrarDiretor(String nomeDiretor) {
        Diretor diretor = new Diretor(nomeDiretor);
        diretorRepository.save(diretor);
        System.out.println("Diretor cadastrado com sucesso!");
    }

    public void associarAtoresEDiretorAoFilme(String nomeFilme, List<String> nomesAtores, String nomeDiretor) {
        Filme filme = pesquisarFilmesPeloNomeIgnoreCase(nomeFilme).stream().findFirst().orElse(null);
        if (filme != null) {
            List<Ator> atoresAssociados = buscarAtoresPorNomes(nomesAtores);
            Diretor diretorAssociado = buscarDiretorPeloNome(nomeDiretor);
            if (diretorAssociado != null && !atoresAssociados.isEmpty()) {
                associarAtoresEDiretor(filme, atoresAssociados, diretorAssociado);
            } else {
                System.out.println("Diretor ou atores não encontrados.");
            }
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    private List<Ator> buscarAtoresPorNomes(List<String> nomesAtores) {
        return atorRepository.findByNomeIn(nomesAtores);
    }

    private Diretor buscarDiretorPeloNome(String nomeDiretor) {
        return diretorRepository.findByNome(nomeDiretor);
    }

    private void associarAtoresEDiretor(Filme filme, List<Ator> atores, Diretor diretor) {
        filme.setAtores(atores);
        filme.setDiretor(diretor);
        filmeRepository.save(filme);
    }

    // Outros métodos e lógica relacionada...
}
