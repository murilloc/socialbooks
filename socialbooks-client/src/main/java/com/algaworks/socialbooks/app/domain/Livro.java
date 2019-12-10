package com.algaworks.socialbooks.app.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;


public class Livro {

    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;
    private String editora;
    private String resumo;
    private List<Comentario> comentarios;
    private Autor autor;


    public Livro() {
    }

    public Livro(String nome) {
        this.nome = nome;
    }

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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate data) {
        this.dataPublicacao = data;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
