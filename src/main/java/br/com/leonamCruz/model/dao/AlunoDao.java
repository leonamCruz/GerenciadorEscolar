package br.com.leonamCruz.model.dao;

import br.com.leonamCruz.control.serviceEntidade.ServiceAluno;
import br.com.leonamCruz.util.UtilData;
import br.com.leonamCruz.util.excessao.ExcessaoAlunoRepetidoBancoDeDados;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao implements GenericDao {
    ServiceAluno serviceAluno;
    private static final String SELECT_FROM = "SELECT * from tb_alunos where nome = ? and nascimento = ? and serie = ? ";
    private static final String CADASTRAR_ALUNO = "insert into tb_alunos(nome,nascimento,serie) values (?,?,?)";
    private static final String EXCLUIR_ALUNO = "delete from tb_alunos where id = ?";
    private static final String ACHA_ID = "select * from tb_alunos where id = ?";

    public AlunoDao(ServiceAluno serviceAluno) {
        this.serviceAluno = serviceAluno;
    }

    public AlunoDao() {
    }

    @Override
    public void salvar() throws SQLException {
        verificaRepeticao();

        try (var conexao = Conexao.getConexao(); var stmt = conexao.prepareStatement(CADASTRAR_ALUNO)) {
            stmt.setString(1, serviceAluno.getNome());
            stmt.setDate(2, Date.valueOf(serviceAluno.getNascimento()));
            stmt.setInt(3, serviceAluno.getSerie());

            stmt.execute();
        }
    }

    private void verificaRepeticao() throws SQLException {
        try (var conexao = Conexao.getConexao(); var stmt = conexao.prepareStatement(SELECT_FROM)) {
            stmt.setString(1, serviceAluno.getNome());
            stmt.setDate(2, Date.valueOf(serviceAluno.getNascimento()));
            stmt.setInt(3, serviceAluno.getSerie());

            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                throw new ExcessaoAlunoRepetidoBancoDeDados("Aluno Repetido...");
            }

        }
    }

    @Override
    public void alterar() throws SQLException {

    }

    @Override
    public void excluir() throws SQLException {
        verificaSeIdExiste(serviceAluno.getId());

        try (var conexao = Conexao.getConexao(); var stmt = conexao.prepareStatement(EXCLUIR_ALUNO)) {
            stmt.setInt(1, serviceAluno.getId());
            stmt.execute();
        }
    }

    @Override
    public void pesquisar() throws SQLException {

    }

    private void verificaSeIdExiste(int id) throws SQLException {
        try (var conexao = Conexao.getConexao(); var stmt = conexao.prepareStatement(ACHA_ID)) {
            stmt.setInt(1, id);
            var resultSet = stmt.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                throw new SQLException("Não existe essa ID.");
            }
        }
    }

    public List<ServiceAluno> pesquisarPorId(int id) throws SQLException {
        verificaSeIdExiste(id);

        try (var conexao = Conexao.getConexao(); var stmt = conexao.prepareStatement(ACHA_ID)) {
            stmt.setInt(1, id);
            var resultSet = stmt.executeQuery();
            var lista = new ArrayList<ServiceAluno>();
            if (resultSet.next()) {
                var aluno = new ServiceAluno();
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("nome"));

                var dataBr = UtilData.padraoBrasileiroDeData(resultSet.getString("nascimento"));
                int dia = UtilData.pegaDia(dataBr);
                int mes = UtilData.pegaDia(dataBr);
                int ano = UtilData.pegaAno(dataBr);
                int idade = UtilData.calculaIdade(dia, mes, ano);

                aluno.setNascimento(dataBr);
                aluno.setIdade(idade);
                aluno.setSerie(resultSet.getInt("serie"));
                lista.add(aluno);
                return lista;
            }
        }
        return null;
    }
}
