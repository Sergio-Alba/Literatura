package com.sergio.Libreria.Principal;

import com.sergio.Libreria.model.*;
import com.sergio.Libreria.repository.AutorRepository;
import com.sergio.Libreria.repository.LibroRepository;
import com.sergio.Libreria.services.ConsumoApi;
import com.sergio.Libreria.services.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private AutorRepository autorRepository;
    private LibroRepository libroRepository;
    private List<Libro> libro;
    private List<Autor> autor;



    public Principal( AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    public void mostrarMenu() {
        try {
            int option = -1;
            while (option != 0) {
                var menu = """
                        \nElije una opción atravéz de su numero
                        
                        1 - Buscar Libros por titulo
                        2 - Listar libros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos de un determinado año
                        5 - Listar libros por idioma
                        6 - Top 5 Libros mas Descargados
                        0 - Salir
                        """;
                System.out.println(menu);
                option = teclado.nextInt();
                teclado.nextLine();

                switch (option) {
                    case 1:
                        buscarLibroWeb();
                        break;
                case 2 :
                    mostrarLibrosRegistrados();
                    break;
                case 3 :
                    mostrarAutoresRegistrados();
                    break;
                case 4 :
                    mostrarAutoresVivosDesdeElAnio();
                    break;
                case 5 :
                    mostrarLibrosPorIdioma();
                    break;
                case 6 :
                    mostrarTop5LibrosMasDescargados();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                     System.out.println("Opcion no valida.");
                     break;
                }
            }
        }catch (InputMismatchException e){
            e.getMessage();
        }
    }
    // busca el libro por el nombre desde la api
    private void buscarLibroWeb() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        String tituloLibro = teclado.nextLine();
        // Optenemos una lista
        List<DatosLibros> libroBuscado = convierteALibros(tituloLibro);
        // Verificamos si la lista no esta vacia
        if (libroBuscado.size() != 0){
            DatosAutor datosAutor = libroBuscado.get(0).autores().get(0);

            // Buscamos al autor en la base de datos si ya exixte
            Autor existeAutor = autorRepository.findByNombre(datosAutor.nombre());

            // Verificamos si el autor existe, si existe guarda el libro y lo referencia al Id del autor ya existe
            if (existeAutor != null){
                Libro libro = new Libro(libroBuscado.get(0),existeAutor);
                libroRepository.save(libro);
                System.out.println(libro);
            }else {
                // Si el autor no existe entoces crea uno nuevo
                Autor nuevoAutor = new Autor(datosAutor);
                autorRepository.save(nuevoAutor);
                Libro libro = new Libro(libroBuscado.get(0),nuevoAutor);
                libroRepository.save(libro);
                System.out.println(libro);
            }
        }else {
            System.out.println("No existe un libro con ese nombre");
        }

    }
    // funcion para obtener datos de los libros de la base de datos y mostrar los resultado
    private void mostrarLibrosRegistrados(){
        libro =  libroRepository.findAll();
        System.out.println("Libros registrados en la base de datos");
        libro.stream().sorted(Comparator.comparing(Libro::getTitulo))
                    .forEach(System.out::println);
    }
    // Obtiene los datos del los autores registrados en la base de datos
    private void mostrarAutoresRegistrados(){
        autor = autorRepository.findAll();
        System.out.println("Autores registrados en la base de datos");
        autor.stream()
                .forEach(System.out::println);
    }
    // Obtiene los autores vivos desde un año dado
    private void mostrarAutoresVivosDesdeElAnio(){
        System.out.println("Ingresa el año vivo de autor(es) que desea buscar: ");
        var anio = teclado.nextInt();
        autor = autorRepository.listaAutoresVivosPorAnio(anio);
        autor.stream()
                .forEach(System.out::println);
    }
    // Trae los libros que sean de un idioma  que se le pase
    private void mostrarLibrosPorIdioma(){
        System.out.println("Selecciona el idioma que deseas buscar: ");
        int opcion = -1;
        while (opcion != 0) {
            var opciones = """
                    1- en Ingles
                    2- es Español
                    3- fr Franses
                    4- pt Portugues
                    
                    0- salir
                    """;
            System.out.println(opciones);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    List<Libro> librosEnIngles = libroRepository.findByIdioma("en");
                    librosEnIngles.forEach(System.out::println);
                    break;
                case 2:
                    List<Libro> librosEnEspanol = libroRepository.findByIdioma("es");
                    librosEnEspanol.forEach(System.out::println);
                    break;
                case 3:
                    List<Libro> librosEnFrances = libroRepository.findByIdioma("fr");
                    librosEnFrances.forEach(System.out::println);
                    break;
                case 4:
                    List<Libro> librosEnPortugues = libroRepository.findByIdioma("pt");
                    librosEnPortugues.forEach(System.out::println);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Ningún idioma seleccionado");
            }
        }
    }
    // Obtiene los 5 libros mas descargados de orden desendiente
    private void mostrarTop5LibrosMasDescargados(){
        System.out.println("\nTop 5 libros mas descargados:");
        libro = libroRepository.listarLosTop5LibrosMasDescargados();
        libro.stream()
                .forEach(System.out::println);
    }
    // Convierte de Datos a datosLibros segun el nombre del libro buscado
    private List<DatosLibros> convierteALibros(String tituloLibro) {
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replaceAll(" ", "+"));
        Datos datos = convierteDatos.obtenerDatos(json, Datos.class);
        return datos.libros();
    }
}

