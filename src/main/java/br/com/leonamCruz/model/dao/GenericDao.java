package br.com.leonamCruz.model.dao;

public abstract class GenericDao {

    public void salvar(String sql, Object... parametro) {
        try (var conexao = Conexao.getConexao(); var stmt = conexao.prepareStatement(sql)){
            for (int i = 0; i < parametro.length; i++) {
            stmt.setObject(i + 1,parametro[i]);
            stmt.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void alterar(String sql) {

    }

    public void excluir(String sql) {

    }

    public void pesquisar(String sql){

    }
}
