package com.aluracursos.screenmatch.model;



public enum Categoria { // [info_curso] clase enum (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/info_curso.txt#L1050)

    Accion("Action"),
    Romance("Romance"),
    Comedia("Comedy"),
    Drama("Drama"),
    Crimen("Crime");

    private String categoriaOmdb;

    Categoria (String categoriaOmdb){
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String text){
        for(Categoria categoria : Categoria.values()){ // [comentarios]  (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/Comentarios.txt#L15)
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrda " + text);
    }
    

}
