package com.sergio.Libreria;

import com.sergio.Libreria.Principal.Principal;
import com.sergio.Libreria.model.Autor;
import com.sergio.Libreria.repository.AutorRepository;
import com.sergio.Libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibreriaApplication implements CommandLineRunner {
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibreriaApplication.class, args);
	}

	@Override
	public void run (String... args){
		Principal principal = new Principal(autorRepository, libroRepository);
		principal.mostrarMenu();
	}

}
