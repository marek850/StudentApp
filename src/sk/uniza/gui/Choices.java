package sk.uniza.gui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 4/3/2022 - 6:36 PM
 * Trieda predstavuje dialogove okno na vyber zelanej moznosti na zobrazenie/ vyhladavanie
 * @author marek
 */
public class Choices extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private JPanel buttonpanel;
    private JPanel panel;
    private JComboBox comboBox1;
    private JLabel header;
    private JLabel list;
    private String value;
    private String action;

    /**
     * Vytvori nove dialogove okno
     * @param action Zelana akcia s danym zoznamom(Zobrazit, Vyhladat)
     */
    public Choices(String action) {
        this.action = action;
        this.setSize(300, 200);
        this.setContentPane(this.contentPane);
        this.setModal(true);
        this.getRootPane().setDefaultButton(this.buttonOK);

        this.buttonOK.addActionListener(e -> this.onOK());

        this.buttonCancel.addActionListener(e -> this.onCancel());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Choices.this.onCancel();
            }
        });
        this.customizeDialog();
        this.contentPane.registerKeyboardAction(e -> this.onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        this.setVisible(true);
    }

    /**
     * Upravi vzhlad dialogoveho okna na zaklade zelanej akcie
     */
    private void customizeDialog() {
        switch (this.action) {
            case "search" -> {
                this.header.setText("Vyber co chces vyhladat");
                this.list.setText("Vyhladaj");
                this.comboBox1.removeAllItems();
                this.comboBox1.addItem("Fakultu");
                this.comboBox1.addItem("Odbor");
                this.comboBox1.addItem("Studijnu skupinu");
                this.comboBox1.addItem("Predmet");
                this.comboBox1.addItem("Osobu");
            }
            case "show" -> {
                this.header.setText("Vyber aky zoznam chces zobrazit");
                this.list.setText("Zobrazit zoznam:");
                this.comboBox1.addItem("Fakult");
                this.comboBox1.addItem("Odborov");
                this.comboBox1.addItem("Skupin");
                this.comboBox1.addItem("Predmetov");
                this.comboBox1.addItem("Studentov");
                this.comboBox1.addItem("Ucitelov");
            }
        }
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Nastavi hodnotu atributu value a zatvori dialogove okno
     */
    private void onOK() {
        this.value = (String)this.comboBox1.getSelectedItem();
        this.dispose();
    }

    /**
     * Zatvori dialogove okno
     */
    private void onCancel() {
        // add your code here if necessary
        this.dispose();
    }
}
