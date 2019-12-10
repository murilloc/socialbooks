package com.algaworks.socialbooks.app.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;


public class Autor {


    private Long id;


    private String nome;


    @JsonFormat(pattern = "dd/MM/yyyy")

    private LocalDate dataNascimento;



    private List<Livro> livros;



    private String nacionalidade;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
