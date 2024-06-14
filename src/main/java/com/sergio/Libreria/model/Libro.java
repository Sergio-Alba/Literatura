package com.sergio.Libreria.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private List<String> idioma;
    private Integer descargas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;


    public Libro(){};

    public Libro(DatosLibros d,Autor a){
        this.titulo = d.titulo();
        this.autor = a;
        this.idioma = d.idioma();
        this.descargas = d.descargas();
    }

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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "\n--------------  Libro  --------------" +
                "\n\tTitulo: " + titulo +
                "\n\tAutor: " + autor.getNombre() +
                "\n\tIdiomas: " + idioma.get(0) +
                "\n\tDescargas: " + descargas +
                "\n-------------------------------------";
    }
}
