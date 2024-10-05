import java.time.LocalDateTime;

public class MovimentoEstoque {
    private int id;
    private Produto produto;
    private String tipoMovimento;  // 'ENTRADA' ou 'SAIDA'
    private int quantidade;
    private LocalDateTime dataMovimento;

    public MovimentoEstoque(int id, Produto produto, String tipoMovimento, int quantidade, LocalDateTime dataMovimento) {
        this.id = id;
        this.produto = produto;
        this.tipoMovimento = tipoMovimento;
        this.quantidade = quantidade;
        this.dataMovimento = dataMovimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }
}