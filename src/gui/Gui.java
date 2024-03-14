package gui;

import principal.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame{
    public JPanel panelMain;
    private JButton adicionarButton;
    private JComboBox addSelecComboBox;
    private JComboBox atrSelecComboBox;
    private JButton atrelarButton;
    private JLabel cdRecordsLogo;

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
    }
}
