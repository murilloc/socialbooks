package com.algaworks.socialbooks.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo texto é obrigatrio")
    @Size(max = 1500, message = "O tamanho máximo do comentário é de 1500 caracters")
    @JsonProperty("comentario")
    private String texto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String usuario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIVRO_ID")
    @JsonIgnore // !!!Stackoverflow!!!
    private Livro livro;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
