package com.sergio.Libreria.repository;

import com.sergio.Libreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    Libro findByTituloContainsIgnoreCase(String titulo);
    List<Libro> findAll();
    List<Libro> findByIdioma(String idioma);
    @Query("SELECT l FROM Libro l ORDER BY l.descargas DESC LIMIT 5")
    List<Libro> listarLosTop5LibrosMasDescargados();
}