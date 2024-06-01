package com.sergio.Libreria.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") String autores,
        @JsonAlias("languages") String lenguaje,
        @JsonAlias("download_count") Integer descargas
) {
}
