package com.sergio.Libreria.model;


import jakarta.persistence.*;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String autores;
    private String lenguages;
    private Integer descargas;

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

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getLenguages() {
        return lenguages;
    }

    public void setLenguages(String lenguages) {
        this.lenguages = lenguages;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Libro(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.autores = datosLibros.autores();
        this.lenguages = datosLibros.lenguaje();
        this.descargas = datosLibros.descargas();
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autores='" + autores + '\'' +
                ", lenguages='" + lenguages + '\'' +
                ", descargas=" + descargas;
    }
}
