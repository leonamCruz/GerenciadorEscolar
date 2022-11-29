package br.com.leonamCruz.view;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
                if (UtilNome.maiorQueSetenta(txtNome.getText().length())) {
                    checkBox1.setSelected(false);
                } else if (txtNome.getText().length() == 0 || txtNome.getText().isEmpty()) {
                    checkBox1.setSelected(false);
                } else {
                    checkBox1.setSelected(!UtilNome.temCaracterInvalido(txtNome.getText()) && !txtNome.getText().isEmpty());
                }
            }
        });
        txtNome.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (!txtNome.getText().isEmpty()) {
                    txtNome.setText(UtilNome.normalizaNome(txtNome.getText()));
                }
            }
        });
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
