package sk.uniza.gui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda predstavuje hlavne menu Studentov v GUI
 * @author marek
 */
public class SubjectDescription extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea1;
    private JPanel panel;
    private JPanel mainPanel;
    private JPanel upPanel;
    private JPanel textPanel;
    private String value;
    /**
     * Vytvori nove dialogove okno
     */
    public SubjectDescription() {
        this.setContentPane(this.contentPane);
        this.setModal(true);
        this.getRootPane().setDefaultButton(this.buttonOK);
        this.buttonOK.addActionListener(e -> this.onOK());
        this.buttonCancel.addActionListener(e -> this.onCancel());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SubjectDescription.this.onCancel();
            }
        });
        this.contentPane.registerKeyboardAction(e -> this.onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        this.pack();
        this.setVisible(true);
    }

    public String getValue() {
        return this.value;
    }
    /**
     * Nastavi hodnotu atributu value a zatvori dialogove okno
     */
    private void onOK() {
        this.value = this.textArea1.getText();
        this.dispose();
    }
    /**
     * Zatvori dialogove okno
     */
    private void onCancel() {
        this.dispose();
    }

}
