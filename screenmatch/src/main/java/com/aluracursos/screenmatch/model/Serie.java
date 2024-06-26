package com.aluracursos.screenmatch.model;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import com.aluracursos.screenmatch.service.ConsultaChatGPT;
import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private Integer totalDeTemporadas;
    private Double evaluacion;
    private String poster;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String actores;
    private String sinopsis;
    @Transient
    private List<Episodio>episodios;

    public Serie(DatosSerie datosSerie) {

        this.titulo = datosSerie.titulo();
        this.totalDeTemporadas = datosSerie.totalDeTemporadas();
        this.evaluacion = OptionalDouble.of(Double.valueOf(datosSerie.evaluacion())).orElse(0);
        this.poster = datosSerie.poster();
        this.genero = Categoria.fromString(datosSerie.genero().split(",")[0].trim()); // [comentarios] (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/Comentarios.txt#L17)
        this.actores = datosSerie.actores();
        this.sinopsis = datosSerie.sinopsis();

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

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public void setTotalDeTemporadas(Integer totalDeTemporadas) {
        this.totalDeTemporadas = totalDeTemporadas;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }


    @Override
    public String toString() {
        return "genero = " + genero + ", titulo = " + titulo + ", totalDeTemporadas = " + totalDeTemporadas + ", evaluacion = " + evaluacion
                + ", poster = " + poster +  ", actores = " + actores + ", sinopsis = " + sinopsis;
    }







    

}
