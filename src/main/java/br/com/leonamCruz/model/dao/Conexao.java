package br.com.leonamCruz.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexao() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_alunos", "Cabuloso", "Leonam1!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
