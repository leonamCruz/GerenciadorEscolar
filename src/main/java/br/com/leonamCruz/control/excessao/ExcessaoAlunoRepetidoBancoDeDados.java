package br.com.leonamCruz.control.excessao;

import java.sql.SQLException;

public class ExcessaoAlunoRepetidoBancoDeDados extends SQLException {
    public ExcessaoAlunoRepetidoBancoDeDados(String reason) {
        super(reason);
    }
}
