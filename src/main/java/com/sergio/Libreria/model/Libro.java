package com.sergio.Libreria.model;


import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Integer descargas;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;


    public Libro(){};

    public Libro(DatosLibros d,Autor a){
        this.titulo = d.titulo();
        this.autor = a;
        this.idioma = d.idioma().get(0);
        this.descargas = d.descargas();
    }

    @Override
    public String toString() {
        String nombreAutor = (autor != null) ? autor.getNombre() : "Desconocido";;
        return "\n----------  Libro  ------------------" +
                "\n\tTitulo: " + titulo +
                "\n\tAutor: " + nombreAutor +
                "\n\tIdiomas: " + idioma +
                "\n\tDescargas: " + descargas +
                "\n-------------------------------------";
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }



}
