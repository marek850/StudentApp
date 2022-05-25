package sk.uniza.gui;

import sk.uniza.people.Student;
import sk.uniza.people.Teacher;
import sk.uniza.school.Grade;
import sk.uniza.school.School;
import sk.uniza.school.Subject;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * 4/3/2022 - 6:36 PM
 * Okno GUI sluziace na zapisanie vyslednej znamky studenta z predmetu
 * @author marek
 */
public class AddGrade extends JFrame implements ActionListener {
    private JPanel panel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JLabel subject;
    private JLabel student;
    private JLabel grade;
    private JButton zapisatButton;
    private School school;
    private Teacher teacher;
    private Student stud;
    private Subject chosenSubject;

    /**
     * Vytvori nove okno GUI
     * @param teacher Ucitel, ktory chce zapisat znamku
     * @param school Instancia triedy School pre ktoru bezi aplikacia
     */
    public AddGrade(Teacher teacher, School school) {
        this.setTitle("Zapisovanie znamky");
        this.teacher = teacher;
        this.school = school;
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700, 200);
        this.comboBox3.addItem(Grade.A);
        this.comboBox3.addItem(Grade.B);
        this.comboBox3.addItem(Grade.C);
        this.comboBox3.addItem(Grade.D);
        this.comboBox3.addItem(Grade.Fx);
        this.addActionEvent();
        this.fillComboBox();
    }
    /**
     * Prida tlacidlam posluchaca
     */
    private void addActionEvent() {
        this.comboBox1.addActionListener(this);
        this.comboBox2.addActionListener(this);
        this.zapisatButton.addActionListener(this);
    }

    /**
     * Prida do komponentu comboBox zoznam predmetov daneho ucitela
     */
    private void fillComboBox() {
        for (Map.Entry<String, Subject> stringSubjectEntry : this.teacher.getTeachingSubjects().entrySet()) {
            Subject s = (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue();
            this.comboBox1.addItem(s.getName());
        }
    }
    /**
     * Pridava funkcionalitu vykonavanu pri stlaceni tlacidiel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.comboBox1) {

            Iterator it = this.teacher.getTeachingSubjects().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Subject sub = (Subject)pair.getValue();
                if (sub.getName().equals(this.comboBox1.getSelectedItem().toString())) {
                    this.chosenSubject = sub;
                    this.chosenSubject.getFilledCapacity();
                }
            }
            HashMap<String, Student> students = this.chosenSubject.getStudentsList();
            this.comboBox2.removeAllItems();
            it = students.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Student st = (Student)pair.getValue();
                this.comboBox2.addItem(st.getFullName());
            }

        }
        if (e.getSource() == this.comboBox2) {
            for (Map.Entry<String, Student> stringStudentEntry : this.school.getStudentsList().entrySet()) {
                Student student1 = (Student)((Map.Entry<?, ?>)stringStudentEntry).getValue();
                if (student1.getFullName().equals(Objects.requireNonNull(this.comboBox2.getSelectedItem()).toString())) {
                    this.stud = student1;
                }
            }
        }
        if (e.getSource() == this.zapisatButton) {
            if (this.stud != null) {
                this.stud.addGrade(this.chosenSubject, (Grade)this.comboBox3.getSelectedItem());
                this.dispose();
            }
        }
    }
}
