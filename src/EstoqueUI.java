import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EstoqueUI extends JFrame {

    private JButton btnAddProduto, btnEntrada, btnSaida, btnListarProdutos;
    private JButton btnAddFornecedor, btnExcluirFornecedor;
    private JButton btnAddCategoria, btnExcluirCategoria;
    private JTable tableProdutos;
    private JScrollPane scrollPane;
    private ProdutoDAO produtoDAO;
    private FornecedorDAO fornecedorDAO;
    private CategoriaDAO categoriaDAO;

    public EstoqueUI() {
        produtoDAO = new ProdutoDAO();
        fornecedorDAO = new FornecedorDAO();
        categoriaDAO = new CategoriaDAO();

        setTitle("Sistema de Controle de Estoque");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(45, 45, 45));

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.setBackground(new Color(30, 30, 30));

        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        btnAddProduto = criarBotao("Adicionar Produto", buttonFont, new Color(70, 130, 180));
        btnEntrada = criarBotao("Entrada de Produto", buttonFont, new Color(60, 179, 113));
        btnSaida = criarBotao("Saída de Produto", buttonFont, new Color(255, 69, 0));
        btnListarProdutos = criarBotao("Listar Produtos", buttonFont, new Color(100, 149, 237));

        btnAddFornecedor = criarBotao("Adicionar Fornecedor", buttonFont, new Color(153, 50, 204));
        btnExcluirFornecedor = criarBotao("Excluir Fornecedor", buttonFont, new Color(178, 34, 34));

        btnAddCategoria = criarBotao("Adicionar Categoria", buttonFont, new Color(123, 104, 238));
        btnExcluirCategoria = criarBotao("Excluir Categoria", buttonFont, new Color(139, 0, 0));

        panelBotoes.add(btnAddProduto);
        panelBotoes.add(btnEntrada);
        panelBotoes.add(btnSaida);
        panelBotoes.add(btnListarProdutos);
        panelBotoes.add(btnAddFornecedor);
        panelBotoes.add(btnExcluirFornecedor);
        panelBotoes.add(btnAddCategoria);
        panelBotoes.add(btnExcluirCategoria);

        tableProdutos = new JTable();
        scrollPane = new JScrollPane(tableProdutos);

        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(new BorderLayout());
        panelTabela.setBackground(new Color(50, 50, 50));
        panelTabela.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelBotoes, BorderLayout.NORTH);
        panelPrincipal.add(panelTabela, BorderLayout.CENTER);

        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(logoLabel, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);

        btnAddProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });

        btnEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarMovimento("ENTRADA");
            }
        });

        btnSaida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarMovimento("SAIDA");
            }
        });

        btnListarProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProdutos();
            }
        });

        btnAddFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarFornecedor();
            }
        });

        btnExcluirFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirFornecedor();
            }
        });

        btnAddCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCategoria();
            }
        });

        btnExcluirCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCategoria();
            }
        });
    }

    private JButton criarBotao(String texto, Font fonte, Color corFundo) {
        JButton botao = new JButton(texto);
        botao.setFont(fonte);
        botao.setForeground(Color.WHITE);
        botao.setBackground(corFundo);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        return botao;
    }

    private void adicionarProduto() {
        String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
        String precoStr = JOptionPane.showInputDialog("Digite o preço do produto:");
        String quantidadeStr = JOptionPane.showInputDialog("Digite a quantidade do produto:");
        double preco = Double.parseDouble(precoStr);
        int quantidade = Integer.parseInt(quantidadeStr);

        String fornecedorIdStr = JOptionPane.showInputDialog("Digite o ID do fornecedor:");
        int fornecedorId = Integer.parseInt(fornecedorIdStr);

        Produto produto = new Produto(nome, preco, quantidade);
        produtoDAO.adicionarProduto(produto, fornecedorId);

        JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
    }

    private void registrarMovimento(String tipo) {
        String idStr = JOptionPane.showInputDialog("Digite o ID do produto:");
        String quantidadeStr = JOptionPane.showInputDialog("Digite a quantidade:");
        int id = Integer.parseInt(idStr);
        int quantidade = Integer.parseInt(quantidadeStr);

        if (tipo.equals("ENTRADA")) {
            produtoDAO.atualizarQuantidade(id, quantidade);
            JOptionPane.showMessageDialog(this, "Entrada de produto registrada com sucesso!");
        } else if (tipo.equals("SAIDA")) {
            produtoDAO.atualizarQuantidade(id, -quantidade);
            JOptionPane.showMessageDialog(this, "Saída de produto registrada com sucesso!");
        }
    }

    private void listarProdutos() {
        List<Produto> produtos = produtoDAO.listarProdutos();
        String[] colunas = {"ID", "Nome", "Preço", "Quantidade"};
        Object[][] dados = new Object[produtos.size()][4];

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            dados[i][0] = produto.getId();
            dados[i][1] = produto.getNome();
            dados[i][2] = produto.getPreco();
            dados[i][3] = produto.getQuantidade();
        }

        tableProdutos.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }

    private void adicionarFornecedor() {
        String nome = JOptionPane.showInputDialog("Digite o nome do fornecedor:");
        Fornecedor fornecedor = new Fornecedor(nome);
        fornecedorDAO.adicionarFornecedor(fornecedor);
        JOptionPane.showMessageDialog(this, "Fornecedor adicionado com sucesso!");
    }

    private void excluirFornecedor() {
        String idStr = JOptionPane.showInputDialog("Digite o ID do fornecedor:");
        int id = Integer.parseInt(idStr);
        fornecedorDAO.excluirFornecedor(id);
        JOptionPane.showMessageDialog(this, "Fornecedor excluído com sucesso!");
    }

    private void adicionarCategoria() {
        String nome = JOptionPane.showInputDialog("Digite o nome da categoria:");
        Categoria categoria = new Categoria(nome);
        categoriaDAO.adicionarCategoria(categoria);
        JOptionPane.showMessageDialog(this, "Categoria adicionada com sucesso!");
    }

    private void excluirCategoria() {
        String idStr = JOptionPane.showInputDialog("Digite o ID da categoria:");
        int id = Integer.parseInt(idStr);
        categoriaDAO.excluirCategoria(id);
        JOptionPane.showMessageDialog(this, "Categoria excluída com sucesso!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EstoqueUI frame = new EstoqueUI();
            frame.setVisible(true);
        });
    }
}