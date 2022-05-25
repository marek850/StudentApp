package sk.uniza.gui;

import sk.uniza.school.Faculty;
import sk.uniza.school.FieldOfStudy;
import sk.uniza.school.Group;
import sk.uniza.school.School;
import sk.uniza.search.Searcher;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda riesi vyhladavanie v systeme prostrednictvom GUI
 * @author marek
 */
public class Search extends JFrame implements ActionListener {
    private JPanel panel;
    private JTable table1;
    private JTextField textField1;
    private JButton vyhladajButton;
    private JComboBox comboBox1;
    private JPanel leftPanel;
    private JPanel textPanel;
    private JPanel mainPanel;
    private JPanel rightPanel;
    private JScrollPane scroll;
    private JButton zobrazButton;
    private JButton zobrazInformacnuKartuButton;
    private String choice;
    private DefaultTableModel tableModel;
    private School school;

    /**
     * Vytvori nove okno gui sluziace na vyhladavanie v databaze aplikacie
     * @param choice Vyhladavana cast skoly(Fakulta, Odbor, Skupina)
     * @param school Skola v ktorej datach sa vyhladava
     */
    public Search(String choice, School school) {
        this.school = school;
        this.choice = choice;
        this.tableModel = new DefaultTableModel(
                new Object[][] { },
                new String[] {"ID", "Nazov"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table1.setModel(this.tableModel);
        this.addTooltip(this.table1);
        this.table1.getColumnModel().getColumn(1).setCellRenderer(
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
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.addActionEvent();
        this.fillComboBox(this.choice);
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
     * Prida do komponentu comboBox zoznam fakult,odborov atd. na zaklade vstupneho parametra
     * @param choice Definovanie zoznamu na pridanie do komponentu
     */
    private void fillComboBox(String choice) {
        Iterator it;
        switch (choice) {
            case "Fakulta" -> {
                it = this.school.getFacultyList().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Faculty f = (Faculty) pair.getValue();
                    this.comboBox1.addItem(f.getName());
                }
            }
            case "Odbor" -> {
                it = this.school.getFieldList().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    FieldOfStudy f = (FieldOfStudy) pair.getValue();
                    this.comboBox1.addItem(f.getName());
                }
            }
            case "Skupina" -> {
                it = this.school.getGroupList().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Group g = (Group) pair.getValue();
                    this.comboBox1.addItem(g.getName());
                }
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
    /**
     * Prida tlacidlam posluchaca
     */
    public void addActionEvent() {
        this.vyhladajButton.addActionListener(this);
        this.zobrazButton.addActionListener(this);
        this.zobrazInformacnuKartuButton.addActionListener(this);
    }
    /**
     * Pridava funkcionalitu vykonavanu pri stlaceni tlacidiel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vyhladajButton) {
            switch (this.choice) {
                case "Fakulta" -> {
                    this.clearTable(this.tableModel);
                    if (!this.textField1.getText().isBlank()) {
                        Faculty f = new Searcher().searchFaculties(this.textField1.getText(), this.school.getFacultyList());
                        this.tableModel.addRow(new Object[]{f.getId(), f.getName()});
                    }
                }
                case "Odbor" -> {
                    this.clearTable(this.tableModel);
                    if (!this.textField1.getText().isBlank()) {
                        FieldOfStudy f = new Searcher().searchFields(this.textField1.getText(), this.school.getFieldList());
                        this.tableModel.addRow(new Object[]{f.getId(), f.getName()});
                    }
                }
                case "Skupina" -> {
                    this.clearTable(this.tableModel);
                    if (!this.textField1.getText().isBlank()) {
                        Group g = new Searcher().searchGroup(this.textField1.getText(), this.school.getGroupList());
                        this.tableModel.addRow(new Object[]{g.getId(), g.getName()});
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + this.choice);
            }

        }
        if (e.getSource() == this.zobrazButton) {
            switch (this.choice) {
                case "Fakulta" -> {
                    this.clearTable(this.tableModel);
                    Faculty f = new Searcher().searchFaculties((String)this.comboBox1.getSelectedItem(), this.school.getFacultyList());
                    this.tableModel.addRow(new Object[]{f.getId(), f.getName()});

                }
                case "Odbor" -> {
                    this.clearTable(this.tableModel);
                    FieldOfStudy f = new Searcher().searchFields((String)this.comboBox1.getSelectedItem(), this.school.getFieldList());
                    this.tableModel.addRow(new Object[]{f.getId(), f.getName()});
                }
                case "Skupina" -> {
                    this.clearTable(this.tableModel);
                    Group g = new Searcher().searchGroup((String)this.comboBox1.getSelectedItem(), this.school.getGroupList());
                    this.tableModel.addRow(new Object[]{g.getId(), g.getName()});
                }
                default -> throw new IllegalStateException("Unexpected value: " + this.choice);
            }

        }
        if (e.getSource() == this.zobrazInformacnuKartuButton) {
            switch (this.choice) {
                case "Fakulta" -> new InformationCard(this.school.getFacultyList().get(this.tableModel.getValueAt(0, 0)));
                case "Odbor" -> new InformationCard(this.school.getFieldList().get(this.tableModel.getValueAt(0, 0)));
                case "Skupina" -> new InformationCard(this.school.getGroupList().get(this.tableModel.getValueAt(0, 0)));
                default -> throw new IllegalStateException("Unexpected value: " + this.choice);
            }
        }
    }

}
