package br.com.leonamCruz.view;

import javax.swing.*;
import java.awt.event.*;

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
    private JTable nomeCompletoTable;

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
        botaoCadastrar.addActionListener(e -> {
            cadastrar();
        });
    }

    private void cadastrar() {
        if(checkBox1.isSelected() && checkBox2.isSelected()){
            try {

                JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso","Sucesso",JOptionPane.DEFAULT_OPTION);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null,"Sem Sucesso: + " + e.getMessage(),"Fail",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,"Algum Campo está errado...","Fail",JOptionPane.WARNING_MESSAGE);
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
            System.out.println(exception.getMessage());
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
    }
}
