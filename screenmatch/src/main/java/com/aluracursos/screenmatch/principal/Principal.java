package com.aluracursos.screenmatch.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sound.sampled.SourceDataLine;

import org.springframework.cglib.core.Local;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.model.Episodio;
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
/*         System.out.println("Top 5 episodios");
        datosEpisodios.stream()
                        .filter(e -> !e.Evaluacion().equalsIgnoreCase("N/A"))
                        .peek(e-> System.out.println("Primer filtro (N/A)" + e))
                        .sorted(Comparator.comparing(DatosEpisodio::Evaluacion).reversed())
                        .peek(e-> System.out.println("segunda ordenacion (M>m) mayor a menor" + e))
                        .map(e -> e.titulo().toUpperCase())
                        .peek(e-> System.out.println("Tercer filtro Mayusculas  (m>M)" + e))
                        .limit(5)
                        .forEach(System.out::println);
 */

        //Convirtiendo los datos a una lista del tipo Episodio
        List<Episodio> episodios = temporadas.stream() //stream datos temporada
                            .flatMap(t -> t.episodios().stream()
                                        .map(d -> new Episodio(t.numero(),d)))
                            .collect(Collectors.toList());

//         episodios.forEach(System.out::println);


/*          // Busqueda a partir de un año en especifico 
         System.out.println("Por favor indique el año a partir del cual deseas ver los episodios");
         var fecha = teclado.nextInt();
         teclado.nextLine();

         LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);

         DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
*/
/*          episodios.stream()
                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
                .forEach(e -> System.out.println("Temporada = " + e.getTemporada()
                                                    + "Episodio = " + e.getTitulo()
                                                    + "Fecha de lanzamiento = " + e.getFechaDeLanzamiento().format(dtf)
                                                )
                        ); 
*/


        //Busca espisodios por pedazo del titutlo
/*         System.out.println("Por favior esccribe el titulo del espisodio que desea ver");
        var pedazoTitulo = teclado.nextLine();
        Optional<Episodio> episodioBuscar = episodios.stream() // [info_curso]  (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/info_curso.txt#L827)
                 .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
                 .findFirst(); //  [info_curso]  (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/info_curso.txt#L868)  [comentarios]  (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/Comentarios.txt#L9)
        if(episodioBuscar.isPresent()){
            System.out.println("Episodio encontrado");
            System.out.println("Los datos son  " + episodioBuscar.get()); // [comentarios]  (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/Comentarios.txt#L11)
        }else{
            System.out.println("Episodio no encontrado");
        }
 */

        Map<Integer , Double> evaluacionesPorTemporada = episodios.stream()
                    .filter( e -> e.getEvaluacion() > 0.0)
                    .collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.averagingDouble(Episodio::getEvaluacion)));
        System.out.println(evaluacionesPorTemporada);

    }

   
  

}
