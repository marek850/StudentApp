package sk.uniza.people;

import sk.uniza.school.Subject;
import sk.uniza.school.Faculty;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 3/29/2022 - 3:01 PM
 *
 * @author marek
 */
public class Teacher extends Person implements IUser {
    private Faculty faculty;
    private String password;
    private HashMap<String, Subject> teachingSubjects;

    public Teacher(String id, String name, String surname, String password) {
        super(id, name, surname);
        this.password = password;
        this.teachingSubjects = new HashMap<>();
    }

    public HashMap<String, Subject> getTeachingSubjects() {
        HashMap<String, Subject> subjects = new HashMap<>();
        Iterator it = this.teachingSubjects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            subjects.put((String)pair.getKey(), (Subject)pair.getValue());

        }
        return subjects;
    }

    public void addSubject(Subject subject) {
        if (subject != null) {
            if (this.teachingSubjects.isEmpty()) {
                this.teachingSubjects.put(subject.getId(), subject);
            } else {
                if (!this.teachingSubjects.containsKey(subject.getId())) {
                    this.teachingSubjects.put(subject.getId(), subject);
                }
            }
        }
    }

    public void printSubjects() {
        HashMap<String, Subject> subjects = new HashMap<>();
        Iterator it = this.teachingSubjects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Subject s = (Subject)pair.getValue();
            System.out.println(s.toString());
        }
    }

    public void removeSubject(Subject subject) {
        if (subject != null) {
            if (!this.teachingSubjects.isEmpty()) {
                if (this.teachingSubjects.containsKey(subject.getId())) {
                    this.teachingSubjects.remove(subject.getId());
                }
            }
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String pass) {
        this.password = pass;
    }

    @Override
    public String toString() {
        return "Teacher " + getId() + " " + getFullName();
    }
}
