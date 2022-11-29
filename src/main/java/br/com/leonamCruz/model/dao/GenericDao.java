package br.com.leonamCruz.model.dao;

import java.sql.SQLException;

public interface GenericDao {
    void salvar() throws SQLException;
    void alterar()throws SQLException;
    void excluir()throws SQLException;
    void pesquisar()throws SQLException;
}
