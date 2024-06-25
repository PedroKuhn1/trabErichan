/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pedro
 */
public class Conexao {
        private static final String URL = "jdbc:postgresql://localhost:5432/trabd";
        
   

    public static Connection connect(String seu_usuario, String sua_senha) {
        
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, seu_usuario, sua_senha);
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
        
    }
    
}
