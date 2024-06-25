import classes.Produto;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public List<Produto> buscarTodosProdutos() throws SQLException {
        String sql = "SELECT * FROM tb_produtos";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = Conexao.connect("postgres","postgres");
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigoProduto(rs.getInt("pro_codigo"));
                produto.setDescricaoProduto(rs.getString("pro_descricao"));
                produto.setValorProduto(rs.getDouble("pro_valor"));
                produto.setQuantidadeProduto(rs.getInt("pro_quantidade"));
                produto.setFornecedor(rs.getInt("pro_fornecdor"));
                produtos.add(produto);
            }
        }
        return produtos;
    }
    public boolean inserirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO tb_produtos (pro_descricao, pro_valor, pro_quantidade, pro_fornecedor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.connect("postgres", "postgres");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getDescricaoProduto());
            stmt.setDouble(2, produto.getValorProduto());
            stmt.setInt(3, produto.getQuantidadeProduto());
            stmt.setInt(4, produto.getFornecedor());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
}