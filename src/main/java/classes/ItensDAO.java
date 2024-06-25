package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItensDAO {
    private Connection conn;

    public ItensDAO(Connection conn) {
        this.conn = conn;
    }

    public Itens getItem(long id) {
        String sql = "SELECT * FROM tb_itens WHERE ite_codigo = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Itens item = new Itens();
                item.setCodigoItem(id);
                item.setQuantidade(rs.getInt("ite_quantidade"));
                item.setValorParcial(rs.getDouble("ite_valor_parcial"));
                item.setProdutoId(rs.getLong("ite_produto"));
                item.setVendaId(rs.getLong("ite_venda"));
                return item;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
