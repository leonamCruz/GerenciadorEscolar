package br.com.leonamCruz.model.dao;

import br.com.leonamCruz.control.serviceEntidade.ServiceAluno;
import br.com.leonamCruz.util.UtilData;
import br.com.leonamCruz.util.excessao.ExcessaoAlunoRepetidoBancoDeDados;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class AlunoDao implements GenericDao {
    ServiceAluno serviceAluno;
    private static final String PESQUISA_POR_NOME = "select * from tb_alunos where nome like ?";
    private static final String SELECT_FROM = "SELECT * from tb_alunos where nome = ? and nascimento = ? and serie = ? ";
    private static final String CADASTRAR_ALUNO = "insert into tb_alunos(nome,nascimento,serie) values (?,?,?)";
    private static final String EXCLUIR_ALUNO = "delete from tb_alunos where id = ?";
    private static final String ACHA_ID = "select * from tb_alunos where id = ?";
    private static final String PEGA_TODO_MUNDO = "select * from tb_alunos";

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
        List<ServiceAluno> lista = new ArrayList<>();

        try (var conexao = Conexao.getConexao(); var stmt = conexao.prepareStatement(ACHA_ID)) {
            stmt.setInt(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var alunoService = new ServiceAluno();
                alunoService.setId(resultSet.getInt("id"));
                alunoService.setNome(resultSet.getString("nome"));

                var dataBr = UtilData.padraoBrasileiroDeData(resultSet.getString("nascimento"));
                int dia = UtilData.pegaDia(dataBr);
                int mes = UtilData.pegaMes(dataBr);
                int ano = UtilData.pegaAno(dataBr);
                int idade = UtilData.calculaIdade(dia, mes, ano);

                alunoService.setNascimento(dataBr);
                alunoService.setIdade(idade);
                alunoService.setSerie(resultSet.getInt("serie"));
                lista.add(alunoService);
            }
            return lista;
        }
    }

    public List<ServiceAluno> pesquisarPorNome(String nome) throws SQLException {
        List<ServiceAluno> lista = new ArrayList<>();

        try (var conexao = Conexao.getConexao(); var stmt = conexao.prepareStatement(PESQUISA_POR_NOME)) {
            stmt.setString(1, nome);
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var aluno = new ServiceAluno();
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("nome"));

                var dataBr = UtilData.padraoBrasileiroDeData(resultSet.getString("nascimento"));
                int dia = UtilData.pegaDia(dataBr);
                int mes = UtilData.pegaMes(dataBr);
                int ano = UtilData.pegaAno(dataBr);
                int idade = UtilData.calculaIdade(dia, mes, ano);

                aluno.setNascimento(dataBr);
                aluno.setIdade(idade);
                aluno.setSerie(resultSet.getInt("serie"));
                lista.add(aluno);
            }
            return lista;
        }
    }

    public List<ServiceAluno> pegaTodoMundo() throws SQLException {
        List<ServiceAluno> lista = new ArrayList<>();

        try (var conexao = Conexao.getConexao(); var stmt = conexao.prepareStatement(PEGA_TODO_MUNDO)) {
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var serviceAluno = new ServiceAluno();
                var aluno = new ServiceAluno();
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("nome"));

                var dataBr = UtilData.padraoBrasileiroDeData(resultSet.getString("nascimento"));
                int dia = UtilData.pegaDia(dataBr);
                int mes = UtilData.pegaMes(dataBr);
                int ano = UtilData.pegaAno(dataBr);
                int idade = UtilData.calculaIdade(dia, mes, ano);

                aluno.setNascimento(dataBr);
                aluno.setIdade(idade);
                aluno.setSerie(resultSet.getInt("serie"));
                lista.add(aluno);
            }
        }
        return lista;
    }
}
