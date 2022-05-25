package sk.uniza.gui;

import sk.uniza.people.IUser;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda vytvara nove okno GUI sluziace na zmenu hesla pouzivatelov
 * @author marek
 */
public class PasswordChange extends JFrame implements ActionListener {
    private JPanel panel;
    private JPasswordField passwordField1;
    private JButton zmenitButton;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPasswordField passwordField2;
    private JRadioButton ukazHesloRadioButton;
    private IUser user;

    /**
     * Vytvori nove okno
     * @param user Pouzivatel, ktoremu sa meni heslo
     */
    public PasswordChange(IUser user) {
        this.setTitle("Zmena Hesla");
        this.user = user;
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.addActionEvent();
    }
    /**
     * Prida tlacidlam posluchaca
     */
    public void addActionEvent() {
        this.zmenitButton.addActionListener(this);
        this.ukazHesloRadioButton.addActionListener(this);
    }
    /**
     * Pridava funkcionalitu vykonavanu pri stlaceni tlacidiel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zmenitButton) {
            if (this.passwordField1.getPassword().length > 6 && this.passwordField2.getPassword().length >= 6) {
                if (this.passwordField1.getText().equals(this.passwordField2.getText())) {
                    this.user.changePassword(this.passwordField2.getText());
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Hesla sa nezhoduju");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Heslo musi obsahovat aspon 6 znakov");
            }
        }
        if (e.getSource() == this.ukazHesloRadioButton) {
            if (this.ukazHesloRadioButton.isSelected()) {
                this.passwordField1.setEchoChar((char)0);
                this.passwordField2.setEchoChar((char)0);
            } else {
                this.passwordField2.setEchoChar('*');
                this.passwordField1.setEchoChar('*');
            }
        }
    }
}
