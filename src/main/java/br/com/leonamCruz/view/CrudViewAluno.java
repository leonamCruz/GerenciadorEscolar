package br.com.leonamCruz.view;

import br.com.leonamCruz.control.serviceDao.ServiceAlunoDao;
import br.com.leonamCruz.control.serviceEntidade.ServiceAluno;
import br.com.leonamCruz.util.UtilData;
import br.com.leonamCruz.util.UtilNome;
import br.com.leonamCruz.util.excessao.ExceptionUtilData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class CrudViewAluno {
    private JTabbedPane menu;
    private JPanel root;
    private JTextField txtNome;
    private JTextField txtDia;
    private JTextField txtMes;
    private JTextField txtAno;
    private JComboBox opcSerie;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JButton botaoCadastrar;
    private JTextField txtIdExcluir;
    private JButton excluirButton;
    private JTabbedPane menuPesquisa;
    private JTextField txtIdPesquisa;
    private JButton botaoPesquisaPorId;
    private JTextField txtPesquisaPorNome;
    private JButton botaoPesquisaPorNome;
    private JTable tabela;
    private JButton mostrarTodosAlunosButton;
    private JButton limparPesquisaButton;
    private JTextField textNomeAlterar;
    private JTextField txtDiaAlterar;
    private JTextField txtMesAlterar;
    private JTextField txtAnoAlterar;
    private JComboBox opcSerieAlterar;
    private JTextField txtIdAlterar;
    private JButton cadastrarAlterar;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;

    private JPanel getRoot() {
        return root;
    }

    public CrudViewAluno(short aba) {

        menu.setSelectedIndex(aba);
        txtNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                verificaNome();
            }
        });
        txtNome.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                verificaNome();
                if (!txtNome.getText().isEmpty()) {
                    txtNome.setText(UtilNome.normalizaNome(txtNome.getText()));
                }
            }
        });
        txtDia.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                verificaData();
            }
        });
        txtMes.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                verificaData();

            }
        });
        txtAno.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                verificaData();
            }
        });
        txtDia.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (txtDia.getText().contains("Dia")) {
                    txtDia.setText("");
                }
            }
        });
        txtMes.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (txtMes.getText().contains("Mês")) {
                    txtMes.setText("");
                }
            }
        });
        txtAno.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (txtAno.getText().contains("Ano")) {
                    txtAno.setText("");
                }
            }
        });
        botaoCadastrar.addActionListener(e -> cadastrar());
        excluirButton.addActionListener(e -> excluir());
        botaoPesquisaPorId.addActionListener(e -> listarPorId(Integer.parseInt(txtIdPesquisa.getText())));
        botaoPesquisaPorNome.addActionListener(e -> listarPorNome(txtPesquisaPorNome.getText()));
        limparPesquisaButton.addActionListener(e -> limparPesquisa());
        mostrarTodosAlunosButton.addActionListener(e -> mostrarTodos());
    }

    private void mostrarTodos() {
        try {
            listar(new ServiceAlunoDao().pegaTodoMundo());
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Não conseguir efetuar a pesquisa: " + e.getMessage(), "Erro",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluir() {
        var serviceAluno = new ServiceAluno();
        if(txtIdExcluir.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"O campo está vazio");
        }
        try {
            serviceAluno.setId(Integer.parseInt(txtIdExcluir.getText()));
            new ServiceAlunoDao(serviceAluno).excluir();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso", "Sucesso", JOptionPane.DEFAULT_OPTION);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Fail", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Você colocou algo que não é número","Erro",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparPesquisa() {
        var defaultTableModel = (DefaultTableModel) tabela.getModel();
        defaultTableModel.setRowCount(0);
    }

    private void listarPorNome(String nome) {
        if (txtPesquisaPorNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Caixa de texto vazia", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                listar(new ServiceAlunoDao().pesquisarPorNome(nome + "%"));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Fail " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void listar(List<ServiceAluno> lista) {
        var defaultTableModel = (DefaultTableModel) tabela.getModel();
        defaultTableModel.setRowCount(0);
        for (ServiceAluno serviceAluno : lista) {
            defaultTableModel.addRow(new Object[]{
                    serviceAluno.getId(),
                    serviceAluno.getNome(),
                    serviceAluno.getNascimento(),
                    serviceAluno.getIdade(),
                    serviceAluno.getSerie()
            });
            menuPesquisa.setSelectedIndex(2);
        }
    }

    private void listarPorId(int id) {
        try {
            listar(new ServiceAlunoDao().pesquisarPorId(id));
            menuPesquisa.setSelectedIndex(2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Você digitou algo que não é número","Falha",JOptionPane.WARNING_MESSAGE);
        }
    }

    public void createTable() {
        Object[][] data = {{}};
        tabela.setModel(new DefaultTableModel(data, new String[]{
                "Id",
                "Nome",
                "Nascimento",
                "Idade",
                "Série"
        }));
    }

    private void cadastrar() {
        if (checkBox1.isSelected() && checkBox2.isSelected()) {
            try {
                var serviceAluno = new ServiceAluno();
                int dia, mes, ano;

                dia = Integer.parseInt(txtDia.getText());
                mes = Integer.parseInt(txtMes.getText());
                ano = Integer.parseInt(txtAno.getText());

                String nascimento = ano + "-" + mes + "-" + dia;

                serviceAluno.setNome(txtNome.getText());
                serviceAluno.setNascimento(nascimento);
                serviceAluno.setIdade(UtilData.calculaIdade(dia, mes, ano));
                serviceAluno.setSerie(opcSerie.getSelectedIndex() + 6);

                new ServiceAlunoDao(serviceAluno).salvar();

                JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso", "Sucesso", JOptionPane.DEFAULT_OPTION);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Sem Sucesso: + " + e.getMessage(), "Fail", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Algum Campo está errado...", "Fail", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void verificaData() {
        int dia, mes, ano;
        try {

            dia = Integer.parseInt(txtDia.getText());
            mes = Integer.parseInt(txtMes.getText());
            ano = Integer.parseInt(txtAno.getText());

            var diaValido = UtilData.verificaSeDiaEhValido(dia, mes, ano);
            var mesValido = UtilData.verificaMesEhValido(mes);
            var anoValido = UtilData.verificaAnoValido(ano);

            if (diaValido && mesValido && anoValido) {
                checkBox2.setSelected(true);
            }

        } catch (ExceptionUtilData exceptionUtilData) {
            checkBox2.setSelected(false);
            JOptionPane.showMessageDialog(null, exceptionUtilData.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception exception) {
            checkBox2.setSelected(false);
        }
    }

    private void verificaNome() {
        if (UtilNome.maiorQueSetenta(txtNome.getText().length())) {
            checkBox1.setSelected(false);
        } else if (txtNome.getText().length() == 0 || txtNome.getText().isEmpty()) {
            checkBox1.setSelected(false);
        } else {
            checkBox1.setSelected(!UtilNome.temCaracterInvalido(txtNome.getText()) && !txtNome.getText().isEmpty());
        }
    }

    public static void runCrudView(short aba) {
        var crudView = new CrudViewAluno(aba);
        var frame = new JFrame("Alunos");
        frame.setContentPane(crudView.getRoot());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 200);
        frame.setVisible(true);
        frame.requestFocus();
        crudView.createTable();
    }
}