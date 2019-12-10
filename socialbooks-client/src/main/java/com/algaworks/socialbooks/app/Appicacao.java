package com.algaworks.socialbooks.app;

import com.algaworks.socialbooks.app.client.LivrosClient;
import com.algaworks.socialbooks.app.domain.Livro;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: Murillo Cesar
 */

public class Appicacao {
    public static void main(String[] args) {

        LivrosClient cliente = new LivrosClient("http://localhost:8080", "murillo", "changeme");
        List<Livro> livroList = cliente.listar();

        for (Livro livo : livroList) {
            System.out.println("Livro: " + livo.getNome());
        }

        Livro livro = new Livro();
        livro.setNome("Clean code");
        livro.setEditora("Packtbup");
        livro.setDataPublicacao(LocalDate.of(2019, 11, 10));
        livro.setResumo("Boas práticas de programação");

        String localizacao = cliente.salvar(livro);
        System.out.println("URI do livro salvo: " + localizacao);

        Livro librobuscado = cliente.buscar(localizacao);
        System.out.println(" Livro buscado: " + librobuscado.getNome());

    }
}
