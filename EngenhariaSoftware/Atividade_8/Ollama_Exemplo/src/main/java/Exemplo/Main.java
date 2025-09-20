package Exemplo;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException {
        String prompt;
        String host = "http://127.0.0.1:11434/";
        String modelName = "qwen3:1.7b";
        List<String> prompts = new ArrayList<>();
        String memo;

        OllamaAPI ollamaAPI = new OllamaAPI(host);

        ollamaAPI.setRequestTimeoutSeconds(80);

        System.out.println("Faca sua pergunta:");
        Scanner sc = new Scanner(System.in);
        prompt = "";
        while (!prompt.equals("Sair")) {
            prompt = sc.nextLine();
            System.out.println("Pensando...");
            prompts.add(" (");
            prompts.add("Considere que as minhas outras falas foram:");
            memo = String.join(" /n", prompts);
            OllamaResult result =
                    ollamaAPI.generate(modelName, prompt = (prompt + memo + " /n )"), null);
            prompts.add(prompt);

            System.out.println(result.getResponse());
        };
    }
}

