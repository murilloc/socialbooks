package com.algaworks.socialbooks.resources;


import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

    private final LivrosRepository livrosRepository;

    public LivrosResources(LivrosRepository livrosRepository) {

        this.livrosRepository = livrosRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> listar() {

        return ResponseEntity.ok(livrosRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Livro livro) {

        livro = livrosRepository.save(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {

        Optional<Livro> livro = livrosRepository.findById(id);
        if (livro.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(livro.get());
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {

        try {
            livrosRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Livro livro) {

        livro.setId(id);
        livrosRepository.save(livro);
        return ResponseEntity.noContent().build();
    }


}
