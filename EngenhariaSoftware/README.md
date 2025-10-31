# Atividade 1
### Comentar com suas palavras o primeiro trecho do livro Software Engineering at Google, Oreilly.
#### Trecho 1
What precisely do we mean by software engineering? What distinguishes “software engineering” from “programming” or “computer science”? And why would Google have a unique perspective to add to the corpus of previous software engineering literature written over the past 50 years? The terms “programming” and “software engineering” have been used interchangeably for quite some time in our industry, although each term has a different emphasis and different implications. University students tend to study computer science and get jobs writing code as “programmers.” “Software engineering,” however, sounds more serious, as if it implies the application of some theoretical knowledge to build something real and precise. Mechanical engineers, civil engineers, aeronautical engineers, and those in other engineering disciplines all practice engineering. They all work in the real world and use the application of their theoretical knowledge to create something real. Software engineers also create “something real,” though it is less tangible than the things other engineers create. Unlike those more established engineering professions, current software engineering theory or practice is not nearly as rigorous. Aeronautical engineers must follow rigid guidelines and practices, because errors in their calculations can cause real damage; programming, on the whole, has traditionally not followed such rigorous practices. But, as software becomes more integrated into our lives, we must adopt and rely on more rigorous engineering methods. We hope this book helps others see a path toward more reliable software practices

**R:** O primeiro trecho explica qual a diferença entre o termo engenharia de software comparado a ciências da computação e programação, termos que muitas vezes são confundidos devido a suas similaridades. A principal diferença é que, como outras engenharias, a engenharia de software tem como objetivo resolver problemas reais através de um método rigoroso e certeiro, apesar de não ser tanto quanto uma engenharia aeroespacial, como citado no trecho, além de que o resultado é intangível ao contrário de outras engenharias, um exemplo seria a flexibilidade de armazenamento de um banco de dados de acordo com a quantidade de dados posta nele, com esse processo podendo ser feito manualmente ou automaticamente.

# Atividade 2
### Comentar com suas palavras o segundo trecho do livro Software Engineering at Google, Oreilly.
#### Trecho

Programming Over Time
 
We propose that “software engineering” encompasses not just the act of writing code, but all of the tools and processes an organization uses to build and maintain that code over time. What practices can a software organization introduce that will best keep its code valuable over the long term? How can engineers make a codebase more sustainable and the software engineering discipline itself more rigorous? We don’t have fundamental answers to these questions, but we hope that Google’s collective experience over the past two decades illuminates possible paths toward finding those answers. One key insight we share in this book is that software engineering can be thought of as “programming integrated over time.” What practices can we introduce to our code to make it sustainable—able to react to necessary change—over its life cycle, from conception to introduction to maintenance to deprecation?
 
The book emphasizes three fundamental principles that we feel software organizations should keep in mind when designing, architecting, and writing their code:
 
Time and Change
 
How code will need to adapt over the length of its life
 
Scale and Growth
How an organization will need to adapt as it evolves
 
Trade-offs and Costs
 
How an organization makes decisions, based on the lessons of Time and Change and Scale and Growth
 
**R:** Esse trecho amplia mais o conceito de engenharia de software, em que ela é mais do que apenas programação, ela é a junção disso com a consideração de aspectos como a **resistência ao tempo**, a **escalabilidade**, o **custo** e **tradeoffs**.
- A **resistência ao tempo** nesse caso diz o quanto que um programa pode resistir a passagem de tempo, ou seja, a inovações constantes e se mantendo relevante, esse é um conceito quase que impossível de se adquirir na totalidade de um programa pois isso necessita de uma lógica muito refinada e eficaz.
- A **escalabilidade** é a propriedade de algo aumentar o seu tamanho, porém o mais importante é o quanto que ele pode aumentar e em qual taxa.
- O **custo** inclui tanto o desenvolvimento da aplicação quanto sua manutenção e melhoria contínua para conseguir se manter relevante e atual.
- Por fim os **tradeoffs** são, de forma simples, escolhas que devem ser consideradas em todo o tipo de situação, no cenário de engenharia de software, um exemplo seria o uso de uma linguagem de alto nível ao custo de otimização e velocidade.

# Atividade 3
### Listar e explicar 3 exemplos de tradeoffs
- **Modelo relacional(SQL) X Modelo não relacional(NoSQL):** Refere-se ao modelo de um banco de dados, em que o relacional normalmente se resumi a organizar os dados em forma de tabela, com um *header* e assim organizando de forma melhor as informações postas nesse BD e facilitando na interação com ele, mas não possuindo tanta escalabilidade quanto a um modelo não relacional. Esse modelo não usa essa estruturação tabular, o que faz com que ele seja mais apto a receber informações mais diversas e brutas, além de ter uma menor latência ao passo que cada um é desenvolvido pensando a sua aplicação.
- **LP de alto nível X LP de baixo nível:** Uma LP é separada entre essas duas categorias dependendo de sua "proximidade" com que o usuário pode conversar com a máquina, sendo que uma LP de baico nível proporciona uma maior proximidade e, portanto, oferecem maior controle sobre o hardware subjacente. Enquanto que uma LP de alto nível tem uma linguagem mais abstrata mas tem uma estruturação de códigos mais simples de entender, além de acabarem sendo menores, apesar de acabar sendo mais pesado e não proporcionar tanto controle sobre o hardware, ao contrário de uma LP de baixo nível.
- **Legibilidade X Eficiência:** Isso é aplicado no contexto da formulação de um código, em que para se ter uma maior legibilidade é muitas vezes necessário também aumentar as linhas de código, assim prejudicando a performance geral do programa, claro que isso não é exatamente inevitável, é possível escrever um códigos que possuem uma boa legibilidade ao mesmo tempo que mantém uma alta performance porém, existem casos em que isso pode ser um tanto desafiador, sendo optado muitas vezes a priorização da eficiência com um código mais complexo, principalmente em jogos, engines gráficas, bancos de dados e sistemas operacionais, onde cada segundo conta.

# Atividade 4
### Exemplo de diagrama UML
<img width="693" height="503" alt="image" src="https://github.com/user-attachments/assets/34d2ae17-2726-4820-bac2-27e6f5f9e560" />

# Atividades 5 e 6
### Programa Java a partir do diagrama UML e com a implementação de testes
Link para o código: [Atividades 5 e 6](Atividade_5_e_6)

# Atividade 7
Exemplo de um programa Java feito em sala usando SQLite
Link para o código: [Atividade 7](Atividade_7)

# Atividade 8
Este é um programa simples usando a aplicação Ollama para simular um chat que lembre dos últimos prompts dados.

Link para o código: [Atividades 8](Atividade_8)
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







