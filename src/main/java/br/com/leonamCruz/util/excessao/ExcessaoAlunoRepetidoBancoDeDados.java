package br.com.leonamCruz.util.excessao;

import java.sql.SQLException;

public class ExcessaoAlunoRepetidoBancoDeDados extends SQLException {
    public ExcessaoAlunoRepetidoBancoDeDados(String reason) {
        super(reason);
    }
}
