package com.aluracursos.Liter_Alura.repository;

import com.aluracursos.Liter_Alura.model.Autor;
import com.aluracursos.Liter_Alura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT DISTINCT l FROM Libro l JOIN FETCH l.autor a JOIN FETCH a.libros")
    List<Libro> findAllWithAuthorsAndBooks();


    @Query("SELECT l FROM Libro l WHERE l.idiomas LIKE %:idiomaTeclado%")
    List<Libro> librosPorIdiomas(@Param("idiomaTeclado") String idiomaTeclado);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :ano AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento >= :ano)")
    List<Autor> autoresVivos(@Param("ano") int ano);


}

