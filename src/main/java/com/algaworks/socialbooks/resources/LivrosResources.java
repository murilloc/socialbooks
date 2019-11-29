package com.algaworks.socialbooks.resources;


import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

    private final LivrosRepository livrosRepository;

    public LivrosResources(LivrosRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Livro> listar() {
        return livrosRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void salvar(@RequestBody Livro livro) {
        livrosRepository.save(livro);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Livro buscar(@PathVariable("id") Long id) {
        return livrosRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletar(@PathVariable("id") Long id) {
        livrosRepository.deleteById(id);
    }


}
