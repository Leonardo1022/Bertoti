# Atividade 1
### Comentar com suas palavras o primeiro trecho do livro Software Engineering at Google, Oreilly.
R: O primeiro trecho explica qual a diferença entre o termo engenharia de software comparado a ciências da computação e programação, termos que muitas vezes são confundidos devido a suas similaridades. A principal diferença é que, como outras engenharias, a engenharia de software tem como objetivo resolver problemas reais através de um método rigoroso e certeiro, apesar de não ser tanto quanto uma engenharia aeroespacial, como citado no trecho, além de que o resultado é intangível ao contrário de outras engenharias, um exemplo seria a flexibilidade de armazenamento de um banco de dados de acordo com a quantidade de dados posta nele.

# Atividade 2
### Comentar com suas palavras o segundo trecho do livro Software Engineering at Google, Oreilly.
R: Esse trecho amplia mais o conceito de engenharia de software, em que ela é mais do que apenas programação, ela é a junção disso com a consideração de aspectos como a resistência ao tempo, a escalabilidade e a custo e tradeoffs

# Atividade 3
### Listar e explicar 3 exemplos de tradeoffs
- **Modelo relacional(SQL) X Modelo não relacional(NoSQL):** Refere-se ao modelo de um banco de dados, em que o relacional normalmente se resumi a organizar os dados em forma de tabela, com um *header* e assim organizando de forma melhor as informações postas nesse BD e facilitando na interação com ele, mas não possuindo tanta escalabilidade quanto a um modelo não relacional. Esse modelo não usa essa estruturação tabular, o que faz com que ele seja mais apto a receber informações mais diversas e brutas, além de ter uma menor latência ao passo que cada um é desenvolvido pensando a sua aplicação.
- **LP de alto nível X LP de baixo nível:** Uma LP é separada entre essas duas categorias dependendo de sua "proximidade" com que o usuário pode conversar com a máquina, sendo que uma LP de baico nível proporciona uma maior proximidade e, portanto, oferecem maior controle sobre o hardware subjacente. Enquanto que uma LP de alto nível tem uma linguagem mais abstrata mas tem uma estruturação de códigos mais simples de entender, além de acabarem sendo menores, apesar de acabar sendo mais pesado e não proporcionar tanto controle sobre o hardware, ao contrário de uma LP de baixo nível.
- **Legibilidade X Eficiência:** Isso é aplicado no contexto da formulação de um código, em que para se ter uma maior legibilidade é muitas vezes necessário também aumentar as linhas de código, assim prejudicando a performance geral do programa, claro que isso não é exatamente inevitável, é possível escrever um códigos que possuem uma boa legibilidade ao mesmo tempo que mantém uma alta performance porém, existem casos em que isso pode ser um tanto desafiador, sendo optado muitas vezes a priorização da eficiência com um código mais complexo, principalmente em jogos, engines gráficas, bancos de dados e sistemas operacionais, onde cada segundo conta.

# Atividade 4
### Exemplo de diagrama UML
<img width="625" height="491" alt="image" src="https://github.com/user-attachments/assets/8287d9e6-59f4-46b8-b69b-122751a3f0f3" />

# Atividade 7
Exemplo de um programa Java feito em sala usando SQLite
Link para o código: [Atividade 7](https://github.com/Leonardo1022/Bertoti/tree/main/EngenhariaSoftware/Atividade_7/Biblioteca_BD)

# Atividade 8
Este é um programa simples usando a aplicação Ollama para simular um chat que lembre dos últimos prompts dados.
### Código
#### Main
```java
package Exemplo;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

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
        }
    }
}
```




