package sk.uniza.school;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 3/29/2022 - 3:01 PM
 *
 * @author marek
 */
public class FieldOfStudy extends SchoolStructure {
    private HashMap<String, Group> groupList;
    private HashMap<String, Subject> subjectList;
    public FieldOfStudy(String id, String name) {
        super(id, name);
        this.groupList = new HashMap<>();
        this.subjectList = new HashMap<>();
    }
    public void addToFaculty(Faculty faculty) {
        if (faculty != null) {
            faculty.addFieldOfStudy(this);
        } else {
            System.out.println("Chyba!");
        }
    }

    public void addGroup(Group group) {
        if (group != null) {
            if (this.groupList.isEmpty()) {
                this.groupList.put(group.getId(), group);
            } else {
                if (!this.groupList.containsKey(group.getId())) {
                    this.groupList.put(group.getId(), group);
                }
            }
        } else {
            System.out.println("Chyba!");
        }
    }

    public void addSubject(Subject subject) {
        if (subject != null) {
            if (this.subjectList.isEmpty()) {
                this.subjectList.put(subject.getId(), subject);
            } else {
                if (!this.subjectList.containsKey(subject.getId())) {
                    this.subjectList.put(subject.getId(), subject);
                }
            }
        } else {
            System.out.println("Chyba!");
        }
    }

    public void printSubjects() {
        Iterator it = this.subjectList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Subject s1 = (Subject)pair.getValue();
            System.out.println(s1.toString());
        }
    }

    public void printGroups() {
        Iterator it = this.groupList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Group g1 = (Group)pair.getValue();
            System.out.println(g1.toString());
        }
    }

    public HashMap<String, Subject> getSubjects() {
        HashMap<String, Subject> subjects = new HashMap<>();
        Iterator it = this.subjectList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            subjects.put((String)pair.getKey(), (Subject)pair.getValue());
        }
        return subjects;
    }

    public HashMap<String, Group> getGroups() {
        HashMap<String, Group> groups = new HashMap<>();
        Iterator it = this.groupList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            groups.put((String)pair.getKey(), (Group)pair.getValue());
        }
        return groups;
    }

    @Override
    public String toString() {
        return " FieldOfStudy{} " + super.getId() + " " + super.getName();
    }
}
