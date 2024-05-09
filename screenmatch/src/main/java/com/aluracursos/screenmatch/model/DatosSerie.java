package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/* JsonAlias = solo nos permite leer el contenido que viene en este caso de la api
 * JsonProperty = podemos tanto leer como escribir, osea podemos enviar datos en ese modelo
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(@JsonAlias("Title")String titulo,
                        @JsonAlias("totalSeasons") Integer totalDeTemporadas,
                        @JsonAlias("imdbRating") String evaluacion) {

}
