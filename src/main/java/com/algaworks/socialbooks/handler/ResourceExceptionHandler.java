package com.algaworks.socialbooks.handler;

import com.algaworks.socialbooks.domain.DetalhesErro;
import com.algaworks.socialbooks.services.excelptions.LivroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlerLivroNaoEncontradoException(LivroNaoEncontradoException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo(" O livro não pode ser encontrado");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404"); // !!Importante
        erro.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
