package com.aluracursos.screenmatch.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

public class Principal {
    
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t="; // son variables constantes o que nunca cambian por eso se escribe final y con mayusculas
    private final String API_KEY = "&apikey=c2a9da";
    private ConvierteDatos conversor = new ConvierteDatos();


    public void muestraElMenu(){
        System.out.println("Por favor escribe el nombre de la serie que desas buscar!");
        // busca los datos generales de las series 
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        // busca los datos de todas las temporadas
        		List<DatosTemporadas> temporadas = new ArrayList<>(); /* esta parte nos imprime todas las temporadas ys sus datos de una serie  */
		for (int i = 1; i <= datos.totalDeTemporadas() ; i++) {
			json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
			var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
			temporadas.add(datosTemporadas);
		}
		// temporadas.forEach(System.out::println);

        //mostrar solo el titulo de los episodios para las temporadas
/*         for (int i = 0; i < datos.totalDeTemporadas() ; i++) {
            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios(); // estas primeras dos lineas estan siendo sustituidas por - temporadas.forEach(t  -> t.episodios()
            for (int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());  // estas dos lineas estan sustituidas por - forEach(e -> System.out.println(e.titulo()              
            }
        } 
*/

        // temporadas.forEach(t  -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        //Convertir todas las informaciones a una lista del tipo datos episodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                                                        .flatMap(t -> t.episodios().stream())
                                                        .collect(Collectors.toList());

        //Topo 5 episodios
        System.out.println("Top 5 episodios");
        datosEpisodios.stream()
                        .filter(e -> !e.Evaluacion().equalsIgnoreCase("N/A"))
                        .sorted(Comparator.comparing(DatosEpisodio::Evaluacion).reversed())
                        .limit(5)
                        .forEach(System.out::println);
    }

}
