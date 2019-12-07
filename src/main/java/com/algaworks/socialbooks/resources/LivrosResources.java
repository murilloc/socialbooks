package com.algaworks.socialbooks.resources;


import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.LivrosService;
import com.algaworks.socialbooks.services.excelptions.LivroNaoEncontradoException;
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

    public LivrosResources(LivrosService livrosService) {
        this.livrosService = livrosService;
    }

    private final LivrosService livrosService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> listar() {

        return ResponseEntity.ok(livrosService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Livro livro) {

        livro = livrosService.salvar(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {

        Optional<Livro> livro = livrosService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(livro.get());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {

        livrosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Livro livro) {

        livro.setId(id);
        livrosService.salvar(livro);
        return ResponseEntity.noContent().build();
    }


}
