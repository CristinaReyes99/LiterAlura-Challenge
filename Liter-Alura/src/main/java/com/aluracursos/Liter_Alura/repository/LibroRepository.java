package com.aluracursos.Liter_Alura.repository;

import com.aluracursos.Liter_Alura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository  extends JpaRepository <Libro, Long> {
}
