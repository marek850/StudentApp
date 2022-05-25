package sk.uniza.gui;

import sk.uniza.people.Admin;
import sk.uniza.people.Person;
import sk.uniza.people.Student;
import sk.uniza.people.Teacher;
import sk.uniza.school.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda predstavuje okno GUI ktore sluzi na zobrazovanie informacii o
 * jednotlivych uzivateloch
 * @author marek
 */
public class PersonalCard extends JFrame {
    private JPanel panel;
    private JTable table1;
    private JLabel id;
    private JLabel name;
    private JLabel faculty;
    private JLabel field;
    private JScrollPane scroll;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel group;
    private JLabel whoAmI;
    private Person person;
    private DefaultTableModel tableModel;

    /**
     * Vytvori nove okno GUI a zobrazi informacie o uzivatelovi
     * @param person Osoba, ktorej informacie sa zobrazia
     */
    public PersonalCard(Person person) {
        this.faculty.setVisible(false);
        this.group.setVisible(false);
        this.field.setVisible(false);
        this.id.setText("ID: " + person.getId());
        this.name.setText("Meno: " + person.getTitleInFront() + person.getFullName() + person.getTitleBehind());

        this.tableModel = new DefaultTableModel(
                new Object[][] { },
                new String[] {"ID", "Nazov", "Kapacita", "Popis"}
        );
        this.table1.setModel(this.tableModel);
        this.person = person;
        this.setTitle("Personal Card");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        switch (this.person) {
            case Student student -> {
                this.whoAmI.setText("Status: Student");
                this.faculty.setText("Fakulta: " + student.getFaculty().getName());
                this.faculty.setVisible(true);
                this.field.setVisible(true);
                this.field.setText("Odbor: " + student.getField().getName());
                this.group.setText("Skupina: " + student.getGroup().getName());
                this.group.setVisible(true);
                this.clearTable(this.tableModel);
                Iterator it = student.getSubjects().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Subject subject = (Subject) pair.getValue();
                    this.tableModel.addRow(new Object[]{subject.getId(), subject.getName(), subject.getFilledCapacity() + "/"
                            + subject.getCapacity(), subject.getDescription()});
                }
                this.addTooltip(this.table1);
            }
            case Teacher teacher -> {
                this.whoAmI.setText("Status: Ucitel");
                Iterator it = teacher.getTeachingSubjects().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Subject subject = (Subject) pair.getValue();
                    this.tableModel.addRow(new Object[]{subject.getId(), subject.getName(), subject.getFilledCapacity() + "/"
                            + subject.getCapacity(), subject.getDescription()});
                }
                this.addTooltip(this.table1);
            }
            case Admin admin -> this.whoAmI.setText("Status: Admin");

            case null, default -> throw new IllegalStateException("Unexpected value: " + this.person);
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

}
