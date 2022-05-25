package sk.uniza.gui;

import sk.uniza.people.Student;
import sk.uniza.school.School;
import sk.uniza.school.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda predstavuje hlavne menu Studentov v GUI
 * @author marek
 */
public class StudentWindow extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton prihlasovaniePredmetovButton;
    private JButton odhlasitButton;
    private JButton zmenaHeslaButton;
    private JButton zobrazitPredmetyButton;
    private JButton exportovatPredmetyButton;
    private JTable table1;
    private JButton prihlasitButton;
    private JPanel buttons;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JScrollPane scroll;
    private DefaultTableModel tableModel;
    private DefaultTableModel mySubModel;
    private LoginForm loginFrame;
    private Student loggedStudent;
    private HashMap<String, Subject> fieldSubjects;
    private School school;

    /**
     * Vytvori nove okno GUI
     * @param loginFrame instancia okna na prihlasenie
     * @param student Prihlaseny student
     * @param school Skola pre ktoru bezi aplikacia
     */
    public StudentWindow(LoginForm loginFrame, Student student, School school) {
        this.school = school;
        this.loginFrame = loginFrame;
        this.loggedStudent = student;
        this.tableModel = new DefaultTableModel(
                new Object[][] { },
                new String[] {"ID", "Nazov", "Kapacita", "Popis"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        this.mySubModel = new DefaultTableModel(
                new Object[][] { },
                new String[] {"ID", "Nazov", "Kapacita", "Znamka"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        this.table1.setModel(this.tableModel);
        this.table1.getTableHeader().setReorderingAllowed(false);
        this.prihlasitButton.setVisible(false);
        this.setTitle("Student");
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
                                ((JComponent) component).setToolTipText(
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
    public void addActionEvent() {
        this.prihlasovaniePredmetovButton.addActionListener(this);
        this.odhlasitButton.addActionListener(this);
        this.zobrazitPredmetyButton.addActionListener(this);
        this.exportovatPredmetyButton.addActionListener(this);
        this.zmenaHeslaButton.addActionListener(this);
        this.prihlasitButton.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                StudentWindow.this.saveData();
                StudentWindow.this.loginFrame.setVisible(true);
            }
        });
    }

    /**
     * Ulozi vsetky data do prislusnych suborov
     */
    private void saveData() {
        AdminWindow.save(this.school);
    }
    /**
     * Pridava funkcionalitu vykonavanu pri stlaceni tlacidiel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.exportovatPredmetyButton) {
            this.loggedStudent.exportSubjects();
        }
        if (e.getSource() == this.prihlasovaniePredmetovButton) {
            this.clearTable(this.tableModel);
            this.table1.setModel(this.tableModel);
            this.prihlasitButton.setVisible(true);
            this.prihlasitButton.setText("Prihlasit");
            this.fieldSubjects = this.loggedStudent.getField().getSubjects();
            for (Map.Entry<String, Subject> stringSubjectEntry : this.fieldSubjects.entrySet()) {
                if (!this.loggedStudent.getSubjects().containsValue(((Map.Entry<?, ?>)stringSubjectEntry).getValue())) {
                    Subject s = (Subject) ((Map.Entry<?, ?>)stringSubjectEntry).getValue();
                    this.tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getFilledCapacity() + "/" + s.getCapacity(), s.getDescription()});
                }
            }
            this.addTooltip(this.table1);
        }
        if (e.getSource() == this.odhlasitButton) {
            this.loginFrame.setVisible(true);
            this.saveData();
            this.dispose();
        }
        if (e.getSource() == this.zobrazitPredmetyButton) {
            this.clearTable(this.mySubModel);
            this.table1.setModel(this.mySubModel);
            this.prihlasitButton.setVisible(true);
            this.prihlasitButton.setText("Odhlasit");
            for (Map.Entry<String, Subject> stringSubjectEntry : this.loggedStudent.getSubjects().entrySet()) {
                Subject s = (Subject) ((Map.Entry<?, ?>) stringSubjectEntry).getValue();
                this.mySubModel.addRow(new Object[]{s.getId(), s.getName(), s.getFilledCapacity() + "/" + s.getCapacity(), this.loggedStudent.getSubjectGrade(s)});
            }
            this.addTooltip(this.table1);
        }
        if (e.getSource() == this.zmenaHeslaButton) {
            new PasswordChange(this.loggedStudent);
        }

        if (e.getSource() == this.prihlasitButton) {
            switch (this.prihlasitButton.getText()) {
                case "Prihlasit" -> {
                    if (this.table1.getSelectedRow() >= 0) {
                        this.loggedStudent.addSubject(this.fieldSubjects.get(this.tableModel.getValueAt(this.table1.getSelectedRow(), 0)));
                        this.tableModel.removeRow(this.table1.getSelectedRow());
                    }
                }
                case "Odhlasit" -> {
                    if (this.table1.getSelectedRow() >= 0) {
                        this.loggedStudent.removeSubject(this.loggedStudent.getSubjects().get(this.mySubModel.getValueAt(this.table1.getSelectedRow(), 0)));
                        this.mySubModel.removeRow(this.table1.getSelectedRow());
                    }
                }
                case default -> throw new IllegalStateException("Unexpected value: " + this.prihlasitButton.getText());
            }
        }
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

}
