package sk.uniza.gui;

import sk.uniza.generate.Generate;
import sk.uniza.people.Student;
import sk.uniza.school.School;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda vytvara okno GUI ktore sluzi na pridanie noveho studenta do systemu
 * @author marek
 */
public class StudentAddForm extends JFrame implements ActionListener {
    private JPanel panel;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel labelPanel;
    private JPanel fieldPanel;
    private JTextField textField1;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton pridatButton;
    private JRadioButton bcRadioButton;
    private JRadioButton ingRadioButton;
    private JComboBox<Integer> comboBox1;
    private School school;

    /**
     * Vytvori nove okno GUI, ktore sluzi na pridanie noveho studenta do databazy skoly
     * @param school Skola, do ktorej databazy je pridany novy student
     */
    public StudentAddForm(School school) {
        this.setTitle("Novy Student");
        this.school = school;
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
        this.pridatButton.addActionListener(this);
        this.bcRadioButton.addActionListener(this);
        this.ingRadioButton.addActionListener(this);
    }
    /**
     * Pridava funkcionalitu vykonavanu pri stlaceni tlacidiel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.ingRadioButton) {
            if (this.ingRadioButton.isSelected()) {
                this.comboBox1.removeAllItems();
                this.textField1.setText(this.textField1.getText() + "Bc.");
                this.bcRadioButton.setSelected(false);
                this.comboBox1.addItem(1);
                this.comboBox1.addItem(2);
            }
        }
        if (e.getSource() == this.bcRadioButton) {
            if (this.bcRadioButton.isSelected()) {
                this.ingRadioButton.setSelected(false);
                this.comboBox1.removeAllItems();
                this.comboBox1.addItem(1);
                this.comboBox1.addItem(2);
                this.comboBox1.addItem(3);
            }
        }
        if (e.getSource() == this.pridatButton) {
            if (this.textField4.getText().isBlank() && this.textField6.getText().isBlank()) {
                JOptionPane.showMessageDialog(new JOptionPane(), "Vyplnte potrebne udaje");
            } else {
                if (this.ingRadioButton.isSelected()) {
                    Student student = new Student(new Generate().generateID(6), this.textField4.getText(),
                            this.textField6.getText(),  new Generate().generateString(10), (Integer)this.comboBox1.getSelectedItem(),
                            this.ingRadioButton.getText());
                    student.setGroup(new Generate().generateGroup(this.school.getGroupList()));
                    student.setFaculty(new Generate().generateFaculty(this.school.getFacultyList()));
                    student.setField(new Generate().generateFields(this.school.getFieldList()));
                    this.school.addStudent(student);

                    this.dispose();
                } else if (this.bcRadioButton.isSelected()) {
                    Student student = new Student(new Generate().generateID(6), this.textField4.getText(),
                            this.textField6.getText(),  new Generate().generateString(10),
                            (Integer)this.comboBox1.getSelectedItem(),
                            this.bcRadioButton.getText());
                    student.setGroup(new Generate().generateGroup(this.school.getGroupList()));
                    student.setFaculty(new Generate().generateFaculty(this.school.getFacultyList()));
                    student.setField(new Generate().generateFields(this.school.getFieldList()));
                    this.school.addStudent(student);
                    this.dispose();
                }
            }
        }
    }
}
