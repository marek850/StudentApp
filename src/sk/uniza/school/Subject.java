package sk.uniza.school;


import sk.uniza.people.Teacher;
import sk.uniza.school.FieldOfStudy;
import sk.uniza.school.SchoolStructure;

/**
 * 3/29/2022 - 3:01 PM
 *
 * @author marek
 */
public class Subject extends SchoolStructure {

    private int capacity;
    private int filledCapacity;
    private StringBuilder description = new StringBuilder();
    private Teacher teacher;
    private FieldOfStudy field;

    public Subject(String id, String name, int capacity) {
        super(id, name);
        this.capacity = capacity;
    }

    public void addToField(FieldOfStudy field) {
        field.addSubject(this);
    }

    public FieldOfStudy getField() {
        return this.field;
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

    @Override
    public String toString() {
        return "Subject{}" + this.getId() + " " + this.getName();
    }
}
