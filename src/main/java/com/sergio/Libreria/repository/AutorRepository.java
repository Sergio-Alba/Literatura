package com.sergio.Libreria.repository;

import com.sergio.Libreria.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String autor);
    List<Autor> findAll();

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= : anio AND a.fechaDeFallecimiento >= :anio")
    List<Autor> listaAutoresVivosPorAnio(Integer anio);
}
