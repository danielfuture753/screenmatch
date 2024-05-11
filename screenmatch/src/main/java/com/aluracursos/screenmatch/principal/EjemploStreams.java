package com.aluracursos.screenmatch.principal;

import java.util.List;
import java.util.Arrays;

public class EjemploStreams {

    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("brenda", "daniel");

        nombres.stream()
                .sorted()// esta ordena la lista de manera ordenada ya sea letra o numero 
                .limit(2)// esto es para poner en este caso un limite en el numero de registros que te aparecera en pantalla
                .filter(n -> n.startsWith("L"))// va a encontrar donde la primer letra sea l mayuscula 
                .map(n-> n.toUpperCase())// aqui en este caso cuando encuentre la primera letra l , va a transformar todo ese registro en mayusculas
                .forEach(System.out::println);
    }

}
