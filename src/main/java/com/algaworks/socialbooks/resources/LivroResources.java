package com.algaworks.socialbooks.resources;


import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.services.LivroService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/livros")
public class LivroResources {

    private final LivroService livroService;

    public LivroResources(LivroService livrosService) {
        this.livroService = livrosService;
    }


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Livro>> listar() {

        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livroService.listar());

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {

        livro = livroService.salvar(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {


        Optional<Livro> livro = livroService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(livro.get());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {

        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@Valid @PathVariable Long id, @RequestBody Livro livro) {

        livro.setId(id);
        livroService.atualizar(livro);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId, @Valid @RequestBody Comentario comentario) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        comentario.setUsuario(authentication.getName());

        livroService.salvarComentario(livroId, comentario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/{id}/comentarios", method = RequestMethod.GET)
    public ResponseEntity<List<Comentario>> listarComentario(@PathVariable("id") Long livroId) {

        List<Comentario> comentarios = livroService.listarComentario(livroId);

        return ResponseEntity.status(HttpStatus.OK).body(comentarios);

    }


}
