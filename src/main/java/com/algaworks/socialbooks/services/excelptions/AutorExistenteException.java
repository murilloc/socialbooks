package com.algaworks.socialbooks.services.excelptions;

public class AutorExistenteException extends  RuntimeException {

    public AutorExistenteException(String message) {
        super(message);
    }

    public AutorExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
