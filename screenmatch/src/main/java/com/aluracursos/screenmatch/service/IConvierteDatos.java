package com.aluracursos.screenmatch.service;

/* lA T ES PARA DELARAR DAOTS GENERICOS */

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);
}
