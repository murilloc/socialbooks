package com.algaworks.socialbooks.services.excelptions;

import net.bytebuddy.implementation.bytecode.Throw;

public class LivroNaoEncontradoException extends RuntimeException {



    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public LivroNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }


}
