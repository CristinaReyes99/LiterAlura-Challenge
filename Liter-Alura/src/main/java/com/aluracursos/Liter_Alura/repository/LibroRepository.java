package com.aluracursos.Liter_Alura.repository;

import com.aluracursos.Liter_Alura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT DISTINCT l FROM Libro l JOIN FETCH l.autor a JOIN FETCH a.libros")
    List<Libro> findAllWithAuthorsAndBooks();

//    @Query(value = "SELECT l.* FROM libros l JOIN libro_idiomas li ON l.id = li.libro_id WHERE li.idiomas = :idiomaParam", nativeQuery = true)
//    List<Libro> findByIdioma(String idiomaParam);

    @Query(value = "SELECT l.* FROM libros l JOIN libro_idiomas li ON l.id = li.libro_id WHERE li.idiomas = :idiomaParam", nativeQuery = true)
    List<Libro> findByIdioma(@Param("idiomaParam") String idiomaParam);
}

