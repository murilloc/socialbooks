package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.AutorRepository;
import com.algaworks.socialbooks.services.excelptions.AutorExistenteException;
import com.algaworks.socialbooks.services.excelptions.AutorNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository repository) {

        this.autorRepository = repository;
    }

    public List<Autor> listar() {

        return autorRepository.findAll();
    }


    public Autor salvar(Autor autor) {
        if (autor.getId() != null) {
            Optional<Autor> a = autorRepository.findById(autor.getId());
            if (a.isPresent()) {
                throw new AutorExistenteException("O autor já existe no cadastro");
            }
        }
        return autorRepository.save(autor);
    }


    public Autor buscar(Long id) {
        Optional<Autor> autor = autorRepository.findById(id);

        if (autor.isPresent()) {
            return autor.get();
        }

        throw new AutorNaoEncontradoException("O autor solicitado não existe no cadastro");


    }


}
