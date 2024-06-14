package com.sergio.Libreria.repository;

import com.sergio.Libreria.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreContainsIgnoreCase(String nombreAutor);
    Autor findByNombre(String autor);
}
