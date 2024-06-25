/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author pedro
 */
public class FornecedorDAO {
    private Connection conn;

    public FornecedorDAO(Connection conn) {
        this.conn = conn;
    }

    public Fornecedor getItem(int id) {
        String sql = "SELECT * FROM tb_fornecedores WHERE for_codigo = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCodigoFornecedor(id);
                fornecedor.setDescricaoFronecedorl(rs.getString("for_descricao"));
                
                return fornecedor;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
    



    

