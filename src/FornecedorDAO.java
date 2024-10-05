import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    private Connection conectar() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/estoque", "postgres", "12345678");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void adicionarFornecedor(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedores (nome) VALUES (?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirFornecedor(int id) {
        String sql = "DELETE FROM fornecedores WHERE id = ?";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Fornecedor> listarFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedores";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome")
                );
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fornecedores;
    }
}
