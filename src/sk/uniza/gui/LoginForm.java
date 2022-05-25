package sk.uniza.gui;

import sk.uniza.people.Admin;
import sk.uniza.people.IUser;
import sk.uniza.people.Student;
import sk.uniza.people.Teacher;
import sk.uniza.school.School;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 4/3/2022 - 6:36 PM
 *
 * @author marek
 */
public class LoginForm extends JFrame implements ActionListener {
    private School school;
    private JPanel panel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JCheckBox showPassword;

    public LoginForm(School school) {
        this.school = school;
        this.setTitle("Studentsky System");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.addActionEvent();
    }
    /**
     * Prida tlacidlam posluchaca
     */
    public void addActionEvent() {
        this.loginButton.addActionListener(this);
        this.resetButton.addActionListener(this);
        this.showPassword.addActionListener(this);
    }

    public School getSchool() {
        return this.school;
    }

    /**
     * Pridava funkcionalitu vykonavanu pri stlaceni tlacidiel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.loginButton) {
            String userText;
            String pwdText;
            userText = this.userTextField.getText();
            pwdText = this.passwordField.getText();
            IUser user = this.school.loginAuthentification(userText, pwdText);
            if (user != null) {
                switch (user) {
                    case Student student -> {
                        new StudentWindow(this, student, this.school);
                        this.userTextField.setText("");
                        this.passwordField.setText("");
                        this.setVisible(false);
                    }
                    case Admin admin -> {
                        new AdminWindow(this, admin, this.school);
                        this.userTextField.setText("");
                        this.passwordField.setText("");
                        this.setVisible(false);
                    }
                    case Teacher teacher -> {
                        new TeacherWindow(this, teacher, this.school);
                        this.userTextField.setText("");
                        this.passwordField.setText("");
                        this.setVisible(false);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + user);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Zle prihlasovacie udaje");
            }

        }
        if (e.getSource() == this.resetButton) {
            this.userTextField.setText("");
            this.passwordField.setText("");
        }
        if (e.getSource() == this.showPassword) {
            if (this.showPassword.isSelected()) {
                this.passwordField.setEchoChar((char)0);
            } else {
                this.passwordField.setEchoChar('*');
            }
        }
    }
}
