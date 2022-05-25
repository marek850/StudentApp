package sk.uniza.gui;

import sk.uniza.school.Subject;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda riesi vyhladavanie predmetov prostrednictvom GUI
 * @author marek
 */
public class SearchSubject extends JFrame implements ActionListener {
    private JPanel panel;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JPanel tRightPanel;
    private JPanel tLeftPanel;
    private JTextField textField1;
    private JTable table1;
    private JButton vyhladajButton;
    private JComboBox comboBox1;
    private JScrollPane scroll;
    private JButton pridajPopisButton;
    private JButton zobrazButton;
    private JButton zobrazInformacnuKartuButton;
    private DefaultTableModel tableModel;
    private HashMap<String, Subject> subjects;

    /**
     * Vytvori nove okno GUI na vyhladanie prededmetu v zozname urcenom vstupnym parametrom
     * @param subjects zoznam predmetov
     */
    public SearchSubject(HashMap<String, Subject> subjects) {
        this.subjects = subjects;
        this.tableModel = new DefaultTableModel(
                new Object[][] { },
                new String[] {"ID", "Nazov", "Kapacita", "Popis"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Map.Entry<String, Subject> stringSubjectEntry : this.subjects.entrySet()) {
            Subject s = (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue();
            this.comboBox1.addItem(s.getName());
        }
        this.table1.setModel(this.tableModel);
        this.setTitle("Vyhladavac");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.addActionEvent();
        this.table1.setRowHeight(40);
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
        this.pridajPopisButton.addActionListener(this);
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
            this.clearTable(this.tableModel);
            if (!this.textField1.getText().isBlank()) {
                Subject s = new Searcher().searchSubjects(this.textField1.getText(), this.subjects);
                this.tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getFilledCapacity() + "/"
                                + s.getCapacity(), s.getDescription()});
            }
        }
        if (e.getSource() == this.zobrazButton) {
            this.clearTable(this.tableModel);
            Subject s = new Searcher().searchSubjects((String)this.comboBox1.getSelectedItem(), this.subjects);
            this.tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getFilledCapacity() + "/"
                            + s.getCapacity(), s.getDescription()});
        }
        if (e.getSource() == this.pridajPopisButton) {
            if (this.table1.getRowCount() > 0) {
                SubjectDescription s = new SubjectDescription();
                this.subjects.get(this.tableModel.getValueAt(0, 0)).addDescription(s.getValue());
                this.clearTable(this.tableModel);
            }
        }
        if (e.getSource() == this.zobrazInformacnuKartuButton) {
            new InformationCard(this.subjects.get(this.tableModel.getValueAt(0, 0)));
        }
    }

}
