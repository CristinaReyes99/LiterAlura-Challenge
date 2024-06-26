package com.aluracursos.Liter_Alura.principal;
import com.aluracursos.Liter_Alura.model.*;
import com.aluracursos.Liter_Alura.repository.LibroRepository;
import com.aluracursos.Liter_Alura.service.ConsumoAPI;
import com.aluracursos.Liter_Alura.service.ConvierteDtos;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDtos conversor = new ConvierteDtos();
    private Scanner teclado = new Scanner(System.in);

    private List<DatosLibro> datosLibros  = new ArrayList<>();

    public Principal(LibroRepository repository) {
    }


    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0){
            var menu = """
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
                case 1:
                    buscarLibroAPI();
                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion ...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }

    private void buscarLibroAPI() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + nombreLibro.replace(" ","+"));
        var datos = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datos.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("     LIBRO ENCONTRADO");
            Libro libro = new Libro(libroBuscado.get());
             String ResultadoLibro = """
                
                    ---------LIBRO---------
                    Libro: %s 
                    Autor: %s
                    Idioma: %s
                    Número de descargas: %d
                    -----------------------
                    
                    """.formatted(libro.getTitulo(),
                    libro.getAutor().get(0).nombre(),
                    libro.getIdiomas().get(0),
                    libro.getNumeroDeDescargas());

            System.out.println(ResultadoLibro);

        }else {
            System.out.println("Libro no encontrado" + Libro.class);
        }


    }
}





