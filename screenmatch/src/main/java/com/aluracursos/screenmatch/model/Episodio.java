package com.aluracursos.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {

    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodios;
    private Double evaluacion;
    private LocalDate fechaDeLanzamiento;


    //cconstructor
    public Episodio(Integer numero, DatosEpisodio d) {
        //TODO Auto-generated constructor stub
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodios = d.numeroEpisodio();
        try {
            this.evaluacion = Double.valueOf(d.Evaluacion()) ;    // [comentarios]  (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/Comentarios.txt#L5)
        } catch (NumberFormatException e) {
            this.evaluacion = 0.0;
            // TODO: handle exception
        }

        try {
            this.fechaDeLanzamiento = LocalDate.parse(d.fechaDeLanzamiento());     // [comentarios]  (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/Comentarios.txt#L7)
        } catch (DateTimeParseException e) {
            this.fechaDeLanzamiento = null;
            // TODO: handle exception
        }
    }


    public Integer getTemporada() {
        return temporada;
    }
    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getNumeroEpisodios() {
        return numeroEpisodios;
    }
    public void setNumeroEpisodios(Integer numeroEpisodios) {
        this.numeroEpisodios = numeroEpisodios;
    }
    public Double getEvaluacion() {
        return evaluacion;
    }
    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }
    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }
    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }


    @Override
    public String toString() {
        return "temporada=" + temporada + ", titulo=" + titulo + ", numeroEpisodios=" + numeroEpisodios
                + ", evaluacion=" + evaluacion + ", fechaDeLanzamiento=" + fechaDeLanzamiento;
    }

    

}
