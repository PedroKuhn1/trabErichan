package classes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class VendasDAO {
    public void registrarVenda(Venda venda, List<Itens> itens) throws SQLException {
        String sqlVenda = "INSERT INTO tb_vendas (ven_horario, ven_valor_total, ven_funcionario) VALUES (?, ?, ?)";
        String sqlItem = "INSERT INTO tb_itens (ite_quantidade, ite_valor_parcial, ite_produto, ite_venda) VALUES (?, ?, ?, ?)";
        String sqlUpdateProduto = "UPDATE tb_produtos SET pro_quantidade = pro_quantidade - ? WHERE pro_codigo = ?";

        Connection conn = null;
        PreparedStatement stmtVenda = null;
        PreparedStatement stmtItem = null;
        PreparedStatement stmtUpdateProduto = null;

        try {
            
            conn.setAutoCommit(false); // Desativar o auto-commit para transações

            // Inserir a venda
            stmtVenda = conn.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtVenda.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmtVenda.setDouble(2, venda.getValorTotal());
            stmtVenda.setLong(3, venda.getFuncionario());
            stmtVenda.executeUpdate();

            // Obter o ID da venda gerado automaticamente
            long vendaId;
            try (var rs = stmtVenda.getGeneratedKeys()) {
                if (rs.next()) {
                    vendaId = rs.getLong(1);
                } else {
                    throw new SQLException("Falha ao obter o ID da venda.");
                }
            }

            // Inserir os itens da venda
            stmtItem = conn.prepareStatement(sqlItem);
            stmtUpdateProduto = conn.prepareStatement(sqlUpdateProduto);
            for (Itens item : itens) {
                stmtItem.setInt(1, item.getQuantidade());
                stmtItem.setDouble(2, item.getValorParcial());
                stmtItem.setLong(3, item.getProdutoId());
                stmtItem.setLong(4, vendaId);
                stmtItem.addBatch();

                // Atualizar a quantidade do produto
                stmtUpdateProduto.setInt(1, item.getQuantidade());
                stmtUpdateProduto.setLong(2, item.getProdutoId());
                stmtUpdateProduto.addBatch();
            }
            stmtItem.executeBatch();
            stmtUpdateProduto.executeBatch();

            // Confirmar a transação
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Desfazer a transação em caso de erro
            }
            throw e;
        } finally {
            if (stmtVenda != null) {
                stmtVenda.close();
            }
            if (stmtItem != null) {
                stmtItem.close();
            }
            if (stmtUpdateProduto != null) {
                stmtUpdateProduto.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}
