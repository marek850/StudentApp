package sk.uniza.gui;

import sk.uniza.people.IUser;
import sk.uniza.people.Person;
import sk.uniza.school.School;
import sk.uniza.search.Searcher;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda riesi vyhladavanie uzivatelov prostrednictvom GUI
 * @author marek
 */
public class SearchUser extends JFrame implements ActionListener {
    private JPanel panel;
    private JTextField textField1;
    private JButton vyhladajButton;
    private JComboBox comboBox1;
    private JButton zobrazButton;
    private JTable table1;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JPanel rightPanel;
    private JButton zobrazitOsobnuKartuButton;
    private DefaultTableModel tableModel;
    private Person person;
    private School school;

    /**
     * Vytvori nove okno GUI na vyhladavanie uzivatelov v databaze aplikacie
     * @param school skola, ktorej zoznam uzivatelov sa prehliada
     */
    public SearchUser(School school) {
        this.school = school;
        this.tableModel = new DefaultTableModel(
                new Object[][] { },
                new String[] {"ID", "Titul pred", "Meno", "Priezvisko", "Titul za"}
        );
        this.table1.setModel(this.tableModel);
        for (IUser user : this.school.getUserList()) {
            this.comboBox1.addItem(user.getFullName());
        }
        this.setTitle("Vyhladavanie");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.addActionEvent();
        this.addTooltip(this.table1);
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
        this.zobrazitOsobnuKartuButton.addActionListener(this);
        this.zobrazButton.addActionListener(this);
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
     * Pridava funkcionalitu vykonavanu pri stlaceni tlacidiel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vyhladajButton) {
            this.clearTable(this.tableModel);
            if (!this.textField1.getText().isBlank()) {
                this.person = (Person)new Searcher().searchUser(this.textField1.getText(), this.school.getUserList());
                this.tableModel.addRow(new Object[]{this.person.getId(), this.person.getTitleInFront(), this.person.getName(), this.person.getSurname(), this.person.getTitleBehind()});

            }
        }
        if (e.getSource() == this.zobrazButton) {
            this.clearTable(this.tableModel);
            this.person = (Person)new Searcher().searchUser((String)this.comboBox1.getSelectedItem(),
                    this.school.getUserList());
            this.tableModel.addRow(new Object[]{this.person.getId(), this.person.getTitleInFront(), this.person.getName(), this.person.getSurname(), this.person.getTitleBehind()});

        }
        if (e.getSource() == this.zobrazitOsobnuKartuButton) {
            if (this.person != null) {
                new PersonalCard(this.person);
            }
        }
    }
}
