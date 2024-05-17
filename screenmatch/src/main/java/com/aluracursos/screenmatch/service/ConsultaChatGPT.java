package com.aluracursos.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService; // [info_curso] clase enum (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/info_curso.txt#L1149)

public class ConsultaChatGPT {

    public static String obtenerTraduccion(String texto) {
        OpenAiService service = new OpenAiService("API-KEY");

        CompletionRequest requisicion = CompletionRequest.builder()
                .model("gpt-3.5-turbo-0125")
                .prompt("traduce a español el siguiente texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7) // [comentarios] (file:///F:/CURSO/CURSOS/CLONE_GITHUB/screenmatch/screenmatch/Comentarios.txt#L20)
                .build();

        var respuesta = service.createCompletion(requisicion);
        return respuesta.getChoices().get(0).getText();

    }
}
