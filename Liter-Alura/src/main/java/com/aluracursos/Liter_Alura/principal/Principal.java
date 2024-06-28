package com.aluracursos.Liter_Alura.principal;
import com.aluracursos.Liter_Alura.model.*;
import com.aluracursos.Liter_Alura.repository.LibroRepository;
import com.aluracursos.Liter_Alura.service.ConsumoAPI;
import com.aluracursos.Liter_Alura.service.ConvierteDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.ArrayList;
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
                    String.join(", ", libro.getIdiomas()),
                    libro.getNumeroDeDescargas());
            System.out.println(ResultadoLibro);
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
                        String.join(", ", libro.getIdiomas()),
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
        // Implementar la funcionalidad para listar los autores vivos en un determinado año
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Escribe el idioma para listar los libros (ej. 'en', 'es', 'fr'):");
        String idioma = teclado.nextLine();
        List<Libro> libros = repositorio.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en el idioma: " + idioma);
        } else {
            System.out.println("Lista de libros en el idioma '" + idioma + "':");
            for (Libro libro : libros) {
                System.out.println("---------LIBRO---------");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Idiomas: " + String.join(", ", libro.getIdiomas()));
                System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
                System.out.println("-----------------------");
            }
        }
    }
}