public class Categoria {
    private int id;
    private String nome;

    // Construtor com ID e nome
    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Construtor apenas com nome (para adicionar nova categoria)
    public Categoria(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}