package com.aluracursos.Liter_Alura.principal;
import com.aluracursos.Liter_Alura.model.*;
import com.aluracursos.Liter_Alura.repository.LibroRepository;
import com.aluracursos.Liter_Alura.service.ConsumoAPI;
import com.aluracursos.Liter_Alura.service.ConvierteDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDtos conversor = new ConvierteDtos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository repositorio;

    private List<Libro> libros;

    @Autowired
    public Principal(LibroRepository repositorio, ConsumoAPI consumoAPI, ConvierteDtos conversor) {
        this.repositorio = repositorio;
        this.consumoAPI = consumoAPI;
        this.conversor = conversor;
        this.teclado = new Scanner(System.in);
    }

    public void muestraElMenu() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    ***********************************************
                    1. Buscar libro por titulo
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma

                    0. Salir
                    ***********************************************
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1 -> buscarLibroAPI();
                    case 2 -> listarLibros();
                    case 3 -> listarAutoresRegistrados();
                    case 4 -> listarAutoresVivosEnUnDeterminadoAno();
                    case 5 -> listarLibrosPorIdioma();
                    case 0 -> System.out.println("Cerrando la aplicación ...");
                    default -> System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.nextLine();
            }
        }
    }

    private void buscarLibroAPI() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datos.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            System.out.println("     LIBRO ENCONTRADO");
            Libro libro = new Libro(libroBuscado.get());

            try {
                repositorio.save(libro);
                String ResultadoLibro = """
                        ---------LIBRO---------
                        Libro: %s
                        Autor: %s
                        Idioma: %s
                        Número de descargas: %d
                        -----------------------
                        """.formatted(libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.getIdiomas(),
                        libro.getNumeroDeDescargas());
                System.out.println(ResultadoLibro);
            } catch (DataIntegrityViolationException e) {
                System.out.println("El libro ya existe en la base de datos.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error al guardar el libro: " + e.getMessage());
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    public void listarLibros() {
        libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Lista de libros registrados:");

            for (Libro libro : libros) {
                String imprimirLibro = """
                        ---------LIBRO---------
                         Libro: %s
                         Autor: %s
                         Idioma: %s
                         Número de descargas: %d
                        -----------------------
                        """.formatted(libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.getIdiomas(),
                        libro.getNumeroDeDescargas());
                System.out.println(imprimirLibro);
            }
        }
    }

    private void listarAutoresRegistrados() {
        List<Libro> libros = repositorio.findAllWithAuthorsAndBooks();
        System.out.println("Lista de autores registrados:");
        libros.stream()
                .map(Libro::getAutor)
                .distinct()
                .forEach(autor -> {
                    System.out.println("---------Autor---------");
                    System.out.println("Nombre: " + autor.getNombre());
                    System.out.println("Fecha de nacimiento: " + autor.getFechaDeNacimiento());
                    System.out.println("Fecha de fallecimiento: " + autor.getFechaDeFallecimiento());
                    System.out.println("Libros: ");
                    autor.getLibros().forEach(libro -> System.out.println(" - " + libro.getTitulo()));
                    System.out.println("-----------------------");
                });
    }

    private void listarAutoresVivosEnUnDeterminadoAno() {
        System.out.println("Escribe el año para listar los autores vivos:");
        int ano = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autores = repositorio.autoresVivos(ano);
        if (autores.isEmpty()) {
            System.out.println("No hay autores vivos en el año especificado.");
        } else {
            System.out.println("Autores vivos en el año " + ano + ":");
            for (Autor autor : autores) {
                System.out.println("---------Autor---------");
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de nacimiento: " + autor.getFechaDeNacimiento());
                System.out.println("Fecha de fallecimiento: " + autor.getFechaDeFallecimiento());
                List<Libro> libros = autor.getLibros();
                if (libros.isEmpty()) {
                    System.out.println("No hay libros para este autor.");
                } else {
                    System.out.println("Libros:");
                    for (Libro libro : libros) {
                        System.out.println(" - " + libro.getTitulo());
                    }
                }
                System.out.println("-----------------------");
            }
        }
    }

    private void listarLibrosPorIdioma() {
        String menu = """
                ***********************************************
                1. Listar libros en Español
                2. Listar libros en Inglés
                3. Listar libros en Francés
                4. Listar libros en Italiano
                ***********************************************
                """;
        System.out.println(menu);
        try {
            int opcion = teclado.nextInt();
            teclado.nextLine();
            String idioma = "a";
            switch (opcion) {
                case 1 -> idioma = "es";
                case 2 -> idioma = "en";
                case 3 -> idioma = "fr";
                case 4 -> idioma = "it";
                default -> System.out.println("Opción inválida");
            }
            List<Libro> libros = repositorio.librosPorIdiomas(idioma);
            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados en ese idioma");
            } else {
                System.out.println("Lista de libros en el idioma: " + idioma);
                for (Libro libro : libros) {
                    String imprimirLibro = """
                            ------ LIBRO EN %s ------
                             Libro: %s
                             Autor: %s
                             Idioma: %s
                             Número de descargas: %d
                            -----------------------
                            """.formatted(idioma.toUpperCase(), libro.getTitulo(),
                            libro.getAutor().getNombre(),
                            libro.getIdiomas(),
                            libro.getNumeroDeDescargas());
                    System.out.println(imprimirLibro);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido.");
            teclado.nextLine();
        }

    }
}