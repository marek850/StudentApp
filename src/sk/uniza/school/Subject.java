package sk.uniza.school;


import sk.uniza.people.Student;
import sk.uniza.people.Teacher;

/**
 * 3/29/2022 - 3:01 PM
 * Trieda modeluje vyucovaci predmet na vysokej skole
 * @author marek
 */
public class Subject extends SchoolStructure {

    private int capacity;
    private int filledCapacity;
    private StringBuilder description = new StringBuilder();
    private Teacher teacher;
    /**
     * Vytvori instanciu triedy a priradi hodnoty vstupnych parametrov atributom.
     * @param id Identifikator predmetu
     * @param name Nazov predmetu
     * @param capacity Kapacita predmetu
     */
    public Subject(String id, String name, int capacity) {
        super(id, name);
        this.capacity = capacity;
        this.filledCapacity = this.getStudentsList().size();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getFilledCapacity() {
        return this.filledCapacity;
    }

    public StringBuilder getDescription() {
        return this.description;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addDescription(String description) {
        this.description.append(description);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Odstrani studenta zo zoznamu studentov priradenych danemu predmetu a znizi naplnenu kapacitu predmetu.
     * @param student student na odstranenie
     */
    @Override
    public void removeStudent(Student student) {
        super.removeStudent(student);
        this.filledCapacity--;
    }

    /**
     * Skontroluje naplnenost kapacity predmetu. Ak je volne miesto, priradi predmetu noveho studenta
     * a zvysi naplnenu kapacitu predmetu.
     * @param student Novy student
     */


    public void addStudent(Student student) {
        super.addStudent(student);
        this.filledCapacity++;
    }
    @Override
    public String toString() {
        return "    " + this.getName() + "  ID:  " + this.getId();
    }
}
