package gui;

import principal.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Gui extends JFrame{
    public JPanel panelMain;
    private JButton adicionarButton;
    private JComboBox addSelecComboBox;
    private JComboBox atrSelecComboBox;
    private JButton atrelarButton;
    private JLabel cdRecordsLogo;
    private JComboBox mostrarComboBox;
    private JButton mostrarButton;

    public Gui() {
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addSelecComboBox.getSelectedItem().toString().equals("Musico")){
                    Main.inserirCriador();
                }else if(addSelecComboBox.getSelectedItem().toString().equals("Banda")){
                    Main.inserirCriador();
                }else if(addSelecComboBox.getSelectedItem().toString().equals("Instrumento")){
                    Main.inserirInstrumento();
                }else if(addSelecComboBox.getSelectedItem().toString().equals("Produtor")){
                    Main.inserirProdutor();
                }else if(addSelecComboBox.getSelectedItem().toString().equals("Disco")){
                    Main.inserirDisco();
                }else if(addSelecComboBox.getSelectedItem().toString().equals("Musica")){
                    Main.inserirMusica();
                }
                JOptionPane.showMessageDialog(adicionarButton, addSelecComboBox.getSelectedItem().toString());
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = Objects.requireNonNull(mostrarComboBox.getSelectedItem()).toString();
                switch (selectedItem) {
                    case "Bandas", "Músicos":
                        Main.mostrarCriadores();
                        break;
                    case "Discos":
                        Main.mostrarDiscos();
                        break;
                    case "Instrumentos":
                        Main.mostrarInstrumentos();
                        break;
                    case "Músicas":
                        Main.mostrarMusicas();
                        break;
                    case "Produtores":
                        Main.mostrarProdutores();
                        break;
                    case "ATRELAMENTO PRODUZIDOS":
                        Main.mostrarAtrelamentos(1);
                        break;
                    case "ATRELAMENTO TOCADAS":
                        Main.mostrarAtrelamentos(2);
                        break;
                    case "ATRELAMENTO PARTICIPACAO":
                        Main.mostrarAtrelamentos(3);
                        break;
                    case "ATRELAMENTO INTEGRAÇÃO":
                        Main.mostrarAtrelamentos(4);
                        break;
                    case "ATRELAMENTO LANCAMENTO":
                        Main.mostrarAtrelamentos(5);
                        break;
                    case "ATRELAMENTO INCLUIR":
                        Main.mostrarAtrelamentos(6);
                        break;
                    default:
                        JOptionPane.showMessageDialog(mostrarButton, "Seleção inválida.");
                }
            }
        });
        atrelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = atrSelecComboBox.getSelectedItem().toString();
                switch (selectedItem) {
                    case "Incluir música em um disco":
                        Main.incluir();
                        break;
                    case "Integrar músico a uma banda":
                        Main.integrar();
                        break;
                    case "Atrelar criador a um disco":
                        Main.lancar();
                        break;
                    case "Creditar criadores em músicas":
                        Main.participar();
                        break;
                    case "Atrelar produtor a um disco":
                        Main.produzirDisco();
                        break;
                    case "Atrelar instrumento a um músico":
                        Main.tocar();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + selectedItem);
                }
                JOptionPane.showMessageDialog(atrelarButton, selectedItem);
            }
        });
    }
}
