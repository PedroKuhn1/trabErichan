package classes;

import classes.Funcionarios;
import conexao.Conexao;
import java.sql.*;

public class FuncionarioDAO {
    public void adicionarFuncionario(Funcionarios funcionario) throws SQLException {
        String sql = "INSERT INTO tb_funcionarios (fun_nome, fun_cpf, fun_senha, fun_funcao) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.connect("postgres","postgres");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getFuncao());
            stmt.executeUpdate();
        }
    }

    public Funcionarios buscarFuncionarioPorCPF(String cpf) throws SQLException {
        String sql = "SELECT * FROM tb_funcionarios WHERE fun_cpf = ?";
        try (Connection conn = Conexao.connect("postgres","postgres");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcionarios funcionario = new Funcionarios();
                    funcionario.setCodigo(rs.getLong("fun_codigo"));
                    funcionario.setNome(rs.getString("fun_nome"));
                    funcionario.setCpf(rs.getString("fun_cpf"));
                    funcionario.setSenha(rs.getString("fun_senha"));
                    funcionario.setFuncao(rs.getString("fun_funcao"));
                    return funcionario;
                }
            }
        }
        return null;
    }

    public void atualizarFuncionario(Funcionarios funcionario) throws SQLException {
        String sql = "UPDATE tb_funcionarios SET fun_nome = ?, fun_senha = ?, fun_funcao = ? WHERE fun_cpf = ?";
        try (Connection conn = Conexao.connect("postgres","postgres");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getFuncao());
            stmt.setString(4, funcionario.getCpf());
            stmt.executeUpdate();
        }
    }

    public void deletarFuncionario(String cpf) throws SQLException {
        String sql = "DELETE FROM tb_funcionarios WHERE fun_cpf = ?";
        try (Connection conn = Conexao.connect("postgres","postgres");
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }
    public boolean autenticarFuncionario(String cpf, String senha) throws SQLException {
    String sql = "SELECT * FROM tb_funcionarios WHERE fun_cpf = ? AND fun_senha = ?";
    try (Connection conn = Conexao.connect("postgres","postgres");
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, cpf);
        stmt.setString(2, senha);
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next();
        }
    }
}
}