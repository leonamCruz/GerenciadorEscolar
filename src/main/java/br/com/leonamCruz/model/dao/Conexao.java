package br.com.leonamCruz.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexao() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_alunos", "leonam", "123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
