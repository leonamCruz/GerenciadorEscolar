package br.com.leonamCruz.view;

import br.com.leonamCruz.control.util.UtilMenu;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class MenuPrincipal {
    private JTabbedPane alunos;
    private JLabel saudacoes;
    private JComboBox opcAlunos;
    private JButton botaoVamosLa;
    private JComboBox opcNotas;
    private JButton botaoVamosLaNotas;
    private JPanel root;

    public MenuPrincipal() {

        root.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                saudacoes.setText(new UtilMenu().saudacoes(new Date()));
            }
        });
        botaoVamosLa.addActionListener(e ->{

            CrudViewAluno.runCrudView((short) opcAlunos.getSelectedIndex());

        });
    }

    public JPanel getRoot() {
        return root;
    }
    public static void main(String[] args) {
        var menuPrincipal = new MenuPrincipal();
        JFrame frame = new JFrame("Menu Principal");
        frame.setContentPane(menuPrincipal.getRoot());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(500,175);
        frame.setVisible(true);
        frame.requestFocus();
    }
}
