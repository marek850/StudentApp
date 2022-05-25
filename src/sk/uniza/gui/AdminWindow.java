package sk.uniza.gui;

import sk.uniza.fileHandling.ExportToFile;
import sk.uniza.people.Teacher;
import sk.uniza.school.Faculty;
import sk.uniza.school.FieldOfStudy;
import sk.uniza.school.Group;
import sk.uniza.school.School;
import sk.uniza.school.Subject;
import sk.uniza.search.Searcher;
import sk.uniza.people.Admin;
import sk.uniza.people.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda predstavuje okno grafickeho rozhrania, ktore zobrazuje hlavne menu pre adminov
 * @author marek
 */
public class AdminWindow extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton pridatStudentaButton;
    private JButton zmenaHeslaButton;

    private JButton vyhladatButton;
    private JButton odhlasitButton;
    private JTable table1;
    private JButton exportovatButton;
    private JPanel mainPanel;
    private JButton zobrazitButton;
    private Admin loggedAdmin;
    private DefaultTableModel tableModel;
    private DefaultTableModel studentModel;
    private DefaultTableModel subjectModel;
    private LoginForm loginFrame;
    private School school;
    private DefaultTableModel teacherModel;

    /**
     * Vytvori nove okno GUI
     * @param loginFrame instancia okna na prihlasenie
     * @param admin Prihlaseny admin
     * @param school Skola pre ktoru bezi aplikacia
     */
    public AdminWindow(LoginForm loginFrame, Admin admin, School school) {
        this.school = school;
        this.loginFrame = loginFrame;
        this.loggedAdmin = admin;
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
        this.teacherModel = new DefaultTableModel(

                new Object[][] { },
                new String[] {"ID", "Titul pred menom", "Meno", "Priezvisko", "Titul za menom"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
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
                //all cells false
                return false;
            }
        };
        this.exportovatButton.setVisible(false);
        this.table1.setModel(this.tableModel);
        this.table1.getTableHeader().setReorderingAllowed(false);
        this.setTitle("Admin");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.addActionEvent();
        this.addTooltip(this.table1);
    }
    /**
     * Prida tlacidlam posluchaca
     */
    public void addActionEvent() {
        this.pridatStudentaButton.addActionListener(this);
        this.zmenaHeslaButton.addActionListener(this);
        this.zobrazitButton.addActionListener(this);
        this.vyhladatButton.addActionListener(this);
        this.odhlasitButton.addActionListener(this);
        this.exportovatButton.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AdminWindow.this.loginFrame.setVisible(true);
                AdminWindow.this.saveData();
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
     * Ulozi vsetky data do prislusnych suborov
     */
    private void saveData() {
        save(this.school);
    }

    /**
     * Ulozi vsetky data do prislusnych suborov
     * @param school Skola ktorej data je potrebne ulozit
     */
    static void save(School school) {
        ExportToFile e = new ExportToFile();
        e.exportSubjects(school.getSubjectList(), "ImportData/Subjects");
        e.exportStudents(school.getStudentsList(), "ImportData/Students");
        e.exportTeachers(school.getTeachersList(), "ImportData/Teachers");
        e.exportFields(school.getFieldList(), "ImportData/Fields");
        e.exportFaculties(school.getFacultyList(), "ImportData/Faculties");
        e.exportGroups(school.getGroupList(), "ImportData/Groups");
        e.exportAdmins(school.getAdminList(), "ImportData/Admins");

        e.exportGSAssignment(school.getFieldList(), "ImportData/Assignments/Odbory/RozdelenieSkupinAPredmetov");
        e.exportFieldsAssignment(school.getFacultyList(), "ImportData/Assignments/Fakulty/pridelenieOdborov");
        e.exportTeachersAssignment(school.getTeachersList(), "ImportData/Assignments/Ucitelia/rozdeleniePredmetov");
        e.exportStudentAssignment(school.getStudentsList(), "ImportData/Assignments/Studenti/rozdelenie");
    }
    /**
     * Pridava funkcionalitu vykonavanu pri stlaceni tlacidiel
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.pridatStudentaButton) {
            new StudentAddForm(this.school);
        }
        if (e.getSource() == this.zmenaHeslaButton) {
            new PasswordChange(this.loggedAdmin);
        }
        if (e.getSource() == this.zobrazitButton) {
            this.exportovatButton.setVisible(true);
            Choices ch = new Choices("show");
            String choice = ch.getValue();
            if (choice != null) {
                switch (choice) {
                    case "Fakult":
                        this.clearTable(this.tableModel);
                        this.table1.setModel(this.tableModel);
                        Iterator it = this.school.getFacultyList().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            Faculty f = (Faculty)pair.getValue();
                            this.tableModel.addRow(new Object[]{f.getId(), f.getName()});
                        }
                        this.addTooltip(this.table1);
                        break;
                    case "Odborov":
                        this.clearTable(this.tableModel);
                        this.table1.setModel(this.tableModel);
                        it = this.school.getFieldList().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            FieldOfStudy f = (FieldOfStudy)pair.getValue();
                            this.tableModel.addRow(new Object[]{f.getId(), f.getName()});
                        }
                        this.addTooltip(this.table1);
                        break;
                    case "Skupin":
                        this.clearTable(this.tableModel);
                        this.table1.setModel(this.tableModel);
                        it = this.school.getGroupList().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            Group g = (Group)pair.getValue();
                            this.tableModel.addRow(new Object[]{g.getId(), g.getName()});
                        }
                        this.addTooltip(this.table1);
                        break;
                    case "Predmetov":
                        this.clearTable(this.subjectModel);
                        this.table1.setModel(this.subjectModel);
                        it = this.school.getSubjectList().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            Subject s = (Subject)pair.getValue();
                            this.subjectModel.addRow(new Object[]{s.getId(), s.getName(), s.getFilledCapacity() +
                                    "/" + s.getCapacity()});
                        }
                        this.addTooltip(this.table1);
                        break;
                    case "Studentov":
                        this.clearTable(this.studentModel);
                        this.table1.setModel(this.studentModel);
                        it = this.school.getStudentsList().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            Student s = (Student)pair.getValue();
                            this.studentModel.addRow(new Object[]{s.getId(), s.getTitleInFront(),
                                    s.getName(), s.getSurname(), s.getTitleBehind(), s.getYear(), s.getDegree(),
                                    s.getGroup().getName()});
                        }
                        this.addTooltip(this.table1);
                        break;
                    case "Ucitelov":
                        this.clearTable(this.teacherModel);
                        this.table1.setModel(this.teacherModel);
                        it = this.school.getTeachersList().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            Teacher t = (Teacher)pair.getValue();
                            this.teacherModel.addRow(new Object[]{t.getId(), t.getTitleInFront(),
                                    t.getName(), t.getSurname(), t.getTitleBehind()});
                        }
                        this.addTooltip(this.table1);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + choice);
                }
            }
        }
        if (e.getSource() == this.vyhladatButton) {
            this.exportovatButton.setVisible(true);
            Choices ch = new Choices("search");
            String choice = ch.getValue();
            if (choice != null) {
                switch (choice) {
                    case "Fakultu" -> new Search("Fakulta", this.school);
                    case "Odbor" -> new Search("Odbor", this.school);
                    case "Studijnu skupinu" -> new Search("Skupina", this.school);
                    case "Predmet" -> new SearchSubject(this.school.getSubjectList());
                    case "Osobu" -> new SearchUser(this.school);
                }
            }
        }
        if (e.getSource() == this.odhlasitButton) {
            this.loginFrame.setVisible(true);
            this.saveData();
            this.dispose();
        }
        if (e.getSource() == this.exportovatButton) {
            ExportToFile exporter = new ExportToFile();
            if (this.table1.getModel().getRowCount() > 0) {
                if (this.table1.getModel() == this.subjectModel) {
                    exporter.exportSubjects(this.school.getSubjectList(), "ExportData/" +
                            this.school.getName() + "-zoznam predmetov");
                    JOptionPane.showMessageDialog(new JOptionPane(), "Export dat vykonany uspesne");
                    this.clearTable(this.subjectModel);
                    this.exportovatButton.setVisible(false);
                } else if (this.table1.getModel() == this.tableModel) {
                    if (new Searcher().searchFaculties((String)this.tableModel.getValueAt(1, 0),
                            this.school.getFacultyList()) != null) {
                        exporter.exportFaculties(this.school.getFacultyList(), "ExportData/" +
                                this.school.getName() + "-zoznam fakult");
                        JOptionPane.showMessageDialog(new JOptionPane(), "Export dat vykonany uspesne");
                        this.clearTable(this.tableModel);
                        this.exportovatButton.setVisible(false);
                    } else if (new Searcher().searchFields((String)this.tableModel.getValueAt(1, 0),
                            this.school.getFieldList()) != null) {
                        exporter.exportFields(this.school.getFieldList(), "ExportData/" +
                                this.school.getName() + "-zoznam odborov");
                        JOptionPane.showMessageDialog(new JOptionPane(), "Export dat vykonany uspesne");
                        this.clearTable(this.tableModel);
                        this.exportovatButton.setVisible(false);
                    } else if (new Searcher().searchGroup((String)this.tableModel.getValueAt(1, 0),
                            this.school.getGroupList()) != null) {
                        exporter.exportGroups(this.school.getGroupList(), "ExportData/" +
                                this.school.getName() + "-zoznam skupin");
                        JOptionPane.showMessageDialog(new JOptionPane(), "Export dat vykonany uspesne");
                        this.clearTable(this.tableModel);
                        this.exportovatButton.setVisible(false);
                    }

                } else if (this.table1.getModel() == this.studentModel) {
                    exporter.exportStudents(this.school.getStudentsList(), "ExportData/" +
                            this.school.getName() + "-zoznam studentov");
                    JOptionPane.showMessageDialog(new JOptionPane(), "Export dat vykonany uspesne");
                    this.clearTable(this.studentModel);
                    this.exportovatButton.setVisible(false);
                }
                else if (this.table1.getModel() == this.teacherModel) {
                    exporter.exportTeachers(this.school.getTeachersList(), "ExportData/" +
                            this.school.getName() + "-zoznam ucitelov");
                    JOptionPane.showMessageDialog(new JOptionPane(), "Export dat vykonany uspesne");
                    this.clearTable(this.teacherModel);
                    this.exportovatButton.setVisible(false);
                }
            }
        }
    }
}
