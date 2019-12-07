package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.excelptions.LivroNaoEncontradoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {

    private final LivrosRepository livrosRepository;

    public LivrosService(LivrosRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
    }


    public List<Livro> listar() {
        return livrosRepository.findAll();
    }

    public Optional<Livro> buscar(Long id) {
        Optional<Livro> livro = livrosRepository.findById(id);
        if (livro.isPresent()) {
            return livro;
        } else {
            throw new LivroNaoEncontradoException("O livro não foi encontrado");
        }
    }

    public Livro salvar(Livro livro) {
        livro.setId(null);
        return livrosRepository.save(livro);
    }

    public void deletar(Long id) {
        try {
            livrosRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LivroNaoEncontradoException("O livro não foi encontrado");
        }
    }

    public void atualizar(Livro livro){
        verificarExistencia(livro);
        livrosRepository.save(livro);
    }

    private void verificarExistencia(Livro livro){
        buscar(livro.getId());
    }


}
