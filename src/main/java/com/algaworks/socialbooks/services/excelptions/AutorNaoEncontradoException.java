package com.algaworks.socialbooks.services.excelptions;

public class AutorNaoEncontradoException extends RuntimeException {

    public AutorNaoEncontradoException(String message) {
        super(message);
    }

    public AutorNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
