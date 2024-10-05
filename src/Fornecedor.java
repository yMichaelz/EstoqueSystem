public class Fornecedor {
    private int id;
    private String nome;

    public Fornecedor(String nome) {
        this.nome = nome;
    }

    public Fornecedor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}