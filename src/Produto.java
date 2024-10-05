public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private String fornecedoNome;

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getFornecedoNome() {
        return fornecedoNome;
    }

    public void setFornecedoNome(String fornecedoNome) {
        this.fornecedoNome = fornecedoNome;
    }
}