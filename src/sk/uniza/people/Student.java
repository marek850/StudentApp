package sk.uniza.people;

import sk.uniza.fileHandling.ExportToFile;
import sk.uniza.school.Faculty;
import sk.uniza.school.FieldOfStudy;
import sk.uniza.school.Grade;
import sk.uniza.school.Group;
import sk.uniza.school.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * 3/29/2022 - 3:01 PM
 * Trieda modeluje pouzivatela aplikacie Student. Trieda je priamym potomkom abstraktnej triedy Person
 * @author marek
 */
public class Student extends Person {
    private String degree;
    private int year;
    private String pass;
    private Group group;
    private FieldOfStudy field;
    private Faculty faculty;

    private HashMap<String, Subject> subjects = new HashMap<>();
    private HashMap<Subject, Grade> subjectGrades = new HashMap<>();

    /**
     * Vytvori instanciu triedy a nastavi hodnoty urcene vstupnymi parametrami atributom.
     * @param id Identifikator studenta
     * @param name Krstne meno studenta
     * @param surname Priezvisko studenta
     * @param passWord heslo na prihlasenie do systemu
     * @param year rocnik v ktorom student studuje
     * @param degree stupen vysokoskolskeho studia(Bc. alebo Ing)
     */
    public Student(String id, String name, String surname, String passWord, int year, String degree) {
        super(id, name, surname);
        this.degree = degree;
        this.year = year;
        this.pass = passWord;
    }

    public HashMap<String, Subject> getSubjects() {
        HashMap<String, Subject> mySubjects = new HashMap<>();
        for (Map.Entry<String, Subject> stringSubjectEntry : this.subjects.entrySet()) {
            mySubjects.put((String)((Map.Entry<?, ?>)stringSubjectEntry).getKey(), (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue());
        }
        return mySubjects;
    }

    /**
     * Prida do zoznamu predmetov studenta novy predmet, ktory je urceny vstupnym parametrom a
     * priradi studenta danemu predmetu
     * @param subjectToAdd predmet na pridanie do zoznamu
     */
    public void addSubject(Subject subjectToAdd) {
        if (subjectToAdd != null) {
            if (this.subjects.isEmpty()) {
                this.subjects.put(subjectToAdd.getId(), subjectToAdd);
                subjectToAdd.addStudent(this);
            } else {
                if (!this.subjects.containsKey(subjectToAdd.getId())) {
                    this.subjects.put(subjectToAdd.getId(), subjectToAdd);
                    subjectToAdd.addStudent(this);
                }
            }
        } else {
            System.out.println("Pridavany predmet nemoze byt prazdny");
        }
    }

    /**
     * Odoberie zo zoznamu predmetov studenta predmet urceny vstupnym parametrom
     * @param subjectToRemove predmet na odobranie zo zoznamu
     */
    public void removeSubject(Subject subjectToRemove) {
        if (subjectToRemove != null) {
            if (this.subjects.isEmpty()) {
                System.out.println("Zoznam predmetov je prazdny");
            } else {
                if (this.subjects.containsKey(subjectToRemove.getId())) {
                    this.subjects.remove(subjectToRemove.getId());
                    subjectToRemove.removeStudent(this);
                }
            }
        }
    }

    /**
     * Prida vyslednu znamku studenta z predmetu. Predmet a znamka su urcene vstupnymi parametrami
     * @param subjectToGrade predmet, ktoremu je priradena znamka
     * @param grade znamka studenta z predmetu
     */
    public void addGrade(Subject subjectToGrade, Grade grade) {
        if (subjectToGrade != null && grade != null) {
            if (this.subjectGrades.containsKey(subjectToGrade)) {
                this.subjectGrades.replace(subjectToGrade, grade);
            } else {
                this.subjectGrades.put(subjectToGrade, grade);
            }
        } else {
            System.out.println("Chyba! Pre úspešné pridanie známky z predmetu je potrebné zadať správne vstupné parametre");
        }
    }

    /**
     * Vrati znamku studenta z predmetu urceneho vstupnym parametrom
     * @param subject predmet, z ktoreho je potrebne vratit znamku
     * @return znamka z predmetu urceneho parametrom
     */
    public Grade getSubjectGrade(Subject subject) {
        if (subject != null) {
            if (!this.subjectGrades.isEmpty() && this.subjectGrades.containsKey(subject)) {
                return this.subjectGrades.get(subject);
            }
        }
        return null;
    }

    public String getDegree() {
        return this.degree;
    }

    public int getYear() {
        return this.year;
    }

    public Group getGroup() {
        return this.group;
    }

    public FieldOfStudy getField() {
        return this.field;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setGroup(Group group) {
        this.group = group;
        this.group.addStudent(this);
    }
    public void setField(FieldOfStudy field) {
        this.field = field;
        field.addStudent(this);
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
        faculty.addStudent(this);
    }

    /**
     * Zapise vsetky predmety studenta do csv suboru s nazvom "MenoStudenta- Predmety" ulozeneho v adresari ExportData
     */
    public void exportSubjects() {
        new ExportToFile().exportSubjects(this.subjects, "ExportData/" + this.getFullName() + " - Predmety");
    }
    @Override
    public String toString() {
        return "    " + this.getFullName() + "\n " + "ID: " + this.getId();
    }

    @Override
    public String getLogin() {
        return super.getId();
    }
    @Override
    public String getPassword() {
        return this.pass;
    }
    @Override
    public void changePassword(String pass) {
        this.pass = pass;
    }
}
