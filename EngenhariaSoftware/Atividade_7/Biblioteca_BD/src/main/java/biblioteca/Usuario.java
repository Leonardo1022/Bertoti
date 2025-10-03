package biblioteca;

public class Usuario {
    private String nome;
    private int ra;

    public Usuario(String nome, int ra) {
        this.nome = nome;
        this.ra = ra;
    }

    public String getNome() {

        return this.nome;
    }

    public int getRa() {

        return this.ra;
    }
}

