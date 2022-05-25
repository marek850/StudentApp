package sk.uniza.gui;

import sk.uniza.people.Student;
import sk.uniza.school.*;

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
 * jednotlivych castiach struktury skoly(Fakulta,Odbor,...)
 * @author marek
 */
public class InformationCard extends JFrame {
    private JPanel panel;
    private JTable table1;
    private JTextArea textArea1;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JScrollPane scroll;
    private JLabel name;
    private JLabel id;
    private JLabel fullName;
    private JLabel desc;
    private DefaultTableModel tableModel;
    private SchoolStructure choice;

    /**
     * Vytvori nove okno GUI
     * @param choice Trieda pre ktoru sa zobrazi informacna karta
     */
    public InformationCard(SchoolStructure choice) {
        this.setTitle("Informacna karta");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.tableModel = new DefaultTableModel(
                new Object[][] { },
                new String[] {"ID", "Nazov"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        this.table1.setModel(this.tableModel);
        this.customizeDesign(choice);
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
     * Zobrazi potrebne informacie na zaklade triedy urcenej vstupnym parametrom
     * @param choice Trieda na zobrazenie informacii(Potomok triedy SchoolSctructure)
     */
    private void customizeDesign(SchoolStructure choice) {
        this.choice = choice;
        switch (choice) {
            case Faculty faculty -> {
                this.desc.setVisible(false);
                this.textArea1.setVisible(false);
                this.id.setText("ID: " + faculty.getId());
                this.fullName.setText("Nazov: " + faculty.getName());
                this.name.setText("Zoznam odborov:");
                Iterator it = faculty.getFieldsList().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    FieldOfStudy f = (FieldOfStudy) pair.getValue();
                    this.tableModel.addRow(new Object[]{f.getId(), f.getName()});
                }
            }
            case FieldOfStudy field -> {
                this.desc.setVisible(false);
                this.textArea1.setVisible(false);
                this.id.setText("ID: " + field.getId());
                this.fullName.setText("Nazov: " + field.getName());
                this.name.setText("Zoznam Studijnych skupin:");
                Iterator it = field.getGroups().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Group g = (Group)pair.getValue();
                    this.tableModel.addRow(new Object[]{g.getId(), g.getName()});
                }
            }
            case Group group -> {
                this.desc.setVisible(false);
                this.textArea1.setVisible(false);
                this.id.setText("ID: " + group.getId());
                this.fullName.setText("Nazov: " + group.getName());
                this.name.setText("Zoznam studentov:");
                this.tableModel = new DefaultTableModel(

                        new Object[][] { },
                        new String[] {"ID", "Titul pred menom", "Meno", "Priezvisko", "Titul za menom", "Rocnik", "Stupen", "Skupina"}
                ) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                this.table1.setModel(this.tableModel);
                Iterator it = group.getStudentsList().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Student s = (Student)pair.getValue();
                    this.tableModel.addRow(new Object[]{s.getId(), s.getTitleInFront(),
                            s.getName(), s.getSurname(), s.getTitleBehind(), s.getYear(), s.getDegree(),
                            s.getGroup().getName()});
                }
            }
            case Subject subject -> {
                this.id.setText("ID: " + subject.getId());
                this.fullName.setText("Nazov: " + subject.getName());
                this.tableModel = new DefaultTableModel(

                        new Object[][] { },
                        new String[] {"ID", "Titul pred menom", "Meno", "Priezvisko", "Titul za menom", "Rocnik", "Stupen", "Skupina"}
                ) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        //all cells false
                        return false;
                    }
                };
                this.table1.setModel(this.tableModel);
                this.name.setText("Zoznam studentov:");
                this.textArea1.setText(String.valueOf(subject.getDescription()));
                Iterator it = subject.getStudentsList().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Student s = (Student)pair.getValue();
                    this.tableModel.addRow(new Object[]{s.getId(), s.getTitleInFront(),
                            s.getName(), s.getSurname(), s.getTitleBehind(), s.getYear(), s.getDegree(),
                            s.getGroup().getName()});
                }
            }

            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}
