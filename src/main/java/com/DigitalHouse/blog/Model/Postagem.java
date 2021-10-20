package com.DigitalHouse.blog.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="tb_postagem")
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=5, max=100)
    private String titulo;

    @NotNull
    @Size(min=5, max=500)
    private String texto;

    //    private Date date = new java.sql.Date(System.currentTimeMillis()); //importa do java.util
//    @Temporal(TemporalType.TIMESTAMP)  //para informar que estamos trabalhando com tempo
//    private LocalDateTime dateTime = LocalDateTime.now();

    @Temporal(TemporalType.TIMESTAMP) //para informar que estamos trabalhando com tempo
    private Date data = new java.sql.Date(System.currentTimeMillis());

    //Realiza a ligação com a classe Tema, uma ligação muitos pra um (muitas postagens podem ter um tema)
    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Tema tema;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

//    public LocalDateTime getDate() {
//        return dateTime;
//    }
//
//    public void setDate(LocalDateTime dateTime) {
//        this.dateTime = dateTime;
//    }
}
