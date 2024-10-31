package br.com.villaca;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 10380
 */
public class Principal {

    public static void main(String[] args) throws SQLException {

        ConexaoMySQL.getConexaoMySQL();

        System.out.println(ConexaoMySQL.statusConection());

        Pessoa p = new Pessoa(2, "Villaca", "guilherme.villaca@gmail.com", "05095059967");

        Connection con = ConexaoMySQL.getConexaoMySQL();

        PreparedStatement stmt = null;

        try {

            // Inserindo o comando SQL a ser usado
            stmt = con.prepareStatement("INSERT INTO pessoa VALUES (?, ?, ?, ?)");

            // O método setString, define que o valor passado será do tipo inteiro
            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getCpf());

            // Método responsável por fazer a alteração no banco de dados
            stmt.executeUpdate();

        } catch (SQLException ex) {  // Tratamento das exceções

            System.out.println(ex);

        } finally { // Encerramento da conexão

            con.close();

        }

    }
}
