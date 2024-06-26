package com.sergio.Libreria.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro = new ArrayList<>();

    public Autor(){}
    public Autor(DatosAutor d) {
        this.nombre = d.nombre();
        this.fechaDeNacimiento = d.fechaDeNacimiento();
        this.fechaDeFallecimiento = d.fechaDeFallecimiento();
    }
    @Override
    public String toString() {
        StringBuilder librosStr = new StringBuilder();
        if (libro != null) {
            for (Libro l : libro) {
                librosStr.append("\n\t\t").append(l.getTitulo());
            }
        } else {
            librosStr.append("\n\t\tNo hay libros registrados.");
        }

        return  "\n----------  Autor  ------------------" +
                "\n\tNombre: " + nombre +
                "\n\tFecha De Nacimiento: " + fechaDeNacimiento +
                "\n\tFecha De Fallecimiento: " + fechaDeFallecimiento +
                "\n\tLibros: " + librosStr.toString() +
                "\n-------------------------------------";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

}