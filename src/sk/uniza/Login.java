package sk.uniza;


import javax.swing.*;
import java.awt.*;

/**
 * 4/3/2022 - 6:36 PM
 *
 * @author marek
 */
public class Login extends JFrame {
    private JPanel login;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;
    private JButton button2;

    public Login() {
        this.setTitle("EŠkola");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(626, 417);

        ImageIcon logo = new ImageIcon("src/pictures/logo.jpg");
        this.setIconImage(logo.getImage());
        this.getContentPane().setBackground(new Color(50, 83, 250));

        JLabel header = new JLabel("EŠkola");
        header.setFont(new Font("Verdana", Font.PLAIN, 18));

        this.add(header);
        this.setVisible(true);
    }
}
