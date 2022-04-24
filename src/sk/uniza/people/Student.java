package sk.uniza.people;

import sk.uniza.fileHandling.ExportToFile;
import sk.uniza.school.Subject;
import sk.uniza.school.Faculty;
import sk.uniza.school.FieldOfStudy;
import sk.uniza.school.Group;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 3/29/2022 - 3:01 PM
 *
 * @author marek
 */
public class Student extends Person implements IUser {
    private String degree;
    private int year;
    private String pass;
    private Group group;
    private FieldOfStudy field;
    private Faculty faculty;

    private HashMap<String, Subject> subjects = new HashMap<>();
    private HashMap<Subject, Character> subjectGrades = new HashMap<>();

    public Student(String id, String name, String surname, String passWord, int year, String degree) {
        super(id, name, surname);
        this.degree = degree;
        this.year = year;
        this.pass = passWord;
    }

    public HashMap<String, Subject> getSubjects() {
        HashMap<String, Subject> mySubjects = new HashMap<>();
        Iterator it = this.subjects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            mySubjects.put((String)pair.getKey(), (Subject)pair.getValue());
        }
        return mySubjects;
    }

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
        }
    }

    public void removeSubject(Subject subjectToRemove) {
        if (subjectToRemove != null) {
            if (this.subjects.isEmpty()) {
                this.subjects.remove(subjectToRemove.getId());
                subjectToRemove.removeStudent(this);
            } else {
                if (!this.subjects.containsKey(subjectToRemove.getId())) {
                    this.subjects.remove(subjectToRemove.getId());
                    subjectToRemove.removeStudent(this);
                }
            }
        }
    }

    public void addGrade(Subject subjectToGrade, Character grade) {
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

    public Character getSubjectGrade(Subject subject) {
        if (subject != null) {
            if (!this.subjectGrades.isEmpty() && this.subjectGrades.containsKey(subject)) {
                return this.subjectGrades.get(subject);
            }
        }
        return null;
    }

    public void printSubjects() {
        Iterator it = this.subjects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Subject s1 = (Subject)pair.getValue();
            s1.toString();
        }
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

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGroup(Group group) {
        this.group = group;
        group.addStudent(this);
    }

    public void setField(FieldOfStudy field) {
        this.field = field;
        field.addStudent(this);
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
        faculty.addStudent(this);
    }

    public void exportSubjects() {
        new ExportToFile().exportSubjects(this.subjects, this.getFullName() + " " + this.getSurname() + "- Subjects");
    }

    @Override
    public String toString() {
        return "Student : " + super.getFullName() + " " + super.getSurname() + "\n " + "ID: " + super.getId();
    }
    @Override
    public String getPassword() {
        return this.pass;
    }

    @Override
    public void setPassword(String pass) {
        this.pass = pass;
    }
}
