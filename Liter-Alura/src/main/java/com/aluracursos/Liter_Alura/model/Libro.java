package com.aluracursos.Liter_Alura.model;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;


@Entity
@Table (name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column (unique = true)
    private String titulo;
    private List<String> idiomas;
    private Integer numeroDeDescargas;
    @Transient
    private List<DatosAutor> autor;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idiomas = datosLibro.idiomas();
        this.autor = datosLibro.autor();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }


    @Override
    public String toString() {
        return "Libro: " + titulo + '\'' +
                ", Autor: '" + autor + '\'' +
                ", Idioma: " + idiomas + '\'' +
                ", Total de descargas: " + numeroDeDescargas + '\'';
    }



    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DatosAutor> getAutor() {
        return autor;
    }

    public void setAutor(List<DatosAutor> autor) {
        this.autor = autor;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }
}
