package sk.uniza.gui;

import sk.uniza.fileHandling.ExportToFile;
import sk.uniza.people.Student;
import sk.uniza.people.Teacher;
import sk.uniza.school.School;
import sk.uniza.school.Subject;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda predstavuje hlavne menu ucitelov v GUI
 * @author marek
 */
public class TeacherWindow extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton mojePredmetyButton;
    private JButton zmenaHeslaButton;
    private JButton mojiStudentiButton;
    private JButton zapisatZnamkuButton;
    private JButton odhlasitButton;
    private JTable table1;
    private JButton exportovatButton;
    private JPanel mainPanel;
    private JScrollPane scroll;
    private LoginForm loginFrame;
    private Teacher teacher;
    private DefaultTableModel studentModel;
    private DefaultTableModel subjectModel;
    private School school;

    /**
     * Vytvori nove okno GUI
     * @param loginFrame instancia okna na prihlasenie
     * @param teacher Prihlaseny ucitel
     * @param school Skola pre ktoru bezi aplikacia
     */
    public TeacherWindow(LoginForm loginFrame, Teacher teacher, School school) {
        this.school = school;
        this.teacher = teacher;
        this.loginFrame = loginFrame;
        this.setTitle("Ucitel");
        this.studentModel = new DefaultTableModel(
                new Object[][] { },
                new String[] {"ID", "Titul pred menom", "Meno", "Priezvisko", "Titul za menom", "Rocnik", "Stupen", "Skupina"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        this.subjectModel = new DefaultTableModel(
                new Object[][] { },
                new String[] {"ID", "Nazov", "Naplnena kapacita / kapacita"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.addActionEvent();
        this.addTooltip(this.table1);
    }

    /**
     * Prida bunkam tabulky tooltip kde sa nachadza cely text ktory bunka obsahuje
     * @param table tabulka, ktorej bunkam sa prida tooltip
     */
    private void addTooltip(JTable table) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(
                    new DefaultTableCellRenderer() {
                        @Override
                        public Component getTableCellRendererComponent(JTable table,
                                                                       Object value,
                                                                       boolean selected,
                                                                       boolean focused,
                                                                       int row,
                                                                       int column) {

                            Component component = super.getTableCellRendererComponent(
                                    table, value, selected, focused, row, column);

                            if (component instanceof JComponent) {
                                ((JComponent)component).setToolTipText(
                                        Objects.toString(value, null));
                            }

                            return component;
                        }
                    });
        }

    }
    /**
     * Prida tlacidlam posluchaca
     */
    private void addActionEvent() {
        this.zmenaHeslaButton.addActionListener(this);
        this.odhlasitButton.addActionListener(this);
        this.mojePredmetyButton.addActionListener(this);
        this.mojiStudentiButton.addActionListener(this);
        this.zapisatZnamkuButton.addActionListener(this);
        this.exportovatButton.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                TeacherWindow.this.saveData();
            }
        });
    }
    /**
     * Vymaze vsetky udaje z tabulky
     * @param tableModel model tabulky z ktoreho sa vymazu udaje
     */
    private void clearTable(DefaultTableModel tableModel) {
        if (tableModel.getRowCount() > 0) {
            for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
                tableModel.removeRow(i);
            }
        }
    }
    /**
     * Pridava funkcionalitu vykonavanu pri stlaceni tlacidiel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.zmenaHeslaButton) {
            new PasswordChange(this.teacher);
        }
        if (e.getSource() == this.odhlasitButton) {
            this.loginFrame.setVisible(true);
            this.saveData();
            this.dispose();
        }
        if (e.getSource() == this.mojePredmetyButton) {
            this.clearTable(this.subjectModel);
            this.table1.setModel(this.subjectModel);
            for (Map.Entry<String, Subject> stringSubjectEntry : this.teacher.getTeachingSubjects().entrySet()) {
                Subject s = (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue();
                this.subjectModel.addRow(new Object[]{s.getId(), s.getName(), s.getFilledCapacity() + "/"
                        + s.getCapacity()});
            }

        }
        if (e.getSource() == this.mojiStudentiButton) {
            this.clearTable(this.studentModel);
            this.table1.setModel(this.studentModel);
            for (Map.Entry<String, Subject> stringSubjectEntry : this.teacher.getTeachingSubjects().entrySet()) {
                Subject s = (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue();
                for (Map.Entry<String, Student> stringStudentEntry : s.getStudentsList().entrySet()) {
                    Student student = (Student)((Map.Entry<?, ?>)stringStudentEntry).getValue();
                    this.studentModel.addRow(new Object[]{student.getId(), student.getTitleInFront(),
                            student.getName(), student.getSurname(), student.getTitleBehind(), student.getYear(), student.getDegree(),
                            student.getGroup().getName()});
                }

            }
        }
        if (e.getSource() == this.exportovatButton) {
            if (this.table1.getModel() == this.subjectModel) {
                new ExportToFile().exportSubjects(this.teacher.getTeachingSubjects(),
                        "ExportData/" + this.teacher.getFullName() + "-predmety");
            } else if (this.table1.getModel() == this.studentModel) {
                HashMap<String, Student> studentsForExport = new HashMap<>();
                for (int count = 0; count < this.studentModel.getRowCount(); count++) {
                    studentsForExport.put(this.studentModel.getValueAt(count, 0).toString(),
                            this.school.getStudentsList().get(this.studentModel.getValueAt(count, 0).toString()));
                }
                new ExportToFile().exportStudents(studentsForExport, "ExportData/" + this.teacher.getFullName() + "-studenti");
            }
        }
        if (e.getSource() == this.zapisatZnamkuButton) {
            new AddGrade(this.teacher, this.school);
        }

    }

    /**
     * Ulozi vsetky data do prislusnych suborov
     */
    private void saveData() {
        AdminWindow.save(this.school);
    }
}
