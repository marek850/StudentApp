package sk.uniza.school;

import java.util.HashMap;
import java.util.Map;

/**
 * 3/29/2022 - 3:01 PM
 * Trieda modeluje studijny program/odbor na vysokej skole. Trieda je priamym potomkom triedy SchoolStructure
 * @author marek
 */
public class FieldOfStudy extends SchoolStructure {
    private HashMap<String, Group> groupList;
    private HashMap<String, Subject> subjectList;

    /**
     * Vytvori instanciu triedy a priradi atributom hodnoty urcene vstupnymi parametrami
     * @param id Identifikator odboru
     * @param name Nazov odboru
     */
    public FieldOfStudy(String id, String name) {
        super(id, name);
        this.groupList = new HashMap<>();
        this.subjectList = new HashMap<>();
    }

    /**
     * Prida do zoznamu skupin priradenych odboru novu skupinu urcenu vstupnym parametrom
     * @param group Nova skupina na pridanie do zoznamu
     */
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

    /**
     * Prida do zoznamu predmetov priradenych odboru novy predmet urceny vstupnym parametrom
     * @param subject Novy predmet na pridanie do zoznamu
     */
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

    public HashMap<String, Subject> getSubjects() {
        HashMap<String, Subject> subjects = new HashMap<>();
        for (Map.Entry<String, Subject> stringSubjectEntry : this.subjectList.entrySet()) {
            subjects.put((String)((Map.Entry<?, ?>)stringSubjectEntry).getKey(), (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue());
        }
        return subjects;
    }

    public HashMap<String, Group> getGroups() {
        HashMap<String, Group> groups = new HashMap<>();
        for (Map.Entry<String, Group> stringGroupEntry : this.groupList.entrySet()) {
            groups.put((String)((Map.Entry<?, ?>)stringGroupEntry).getKey(), (Group)((Map.Entry<?, ?>)stringGroupEntry).getValue());
        }
        return groups;
    }

    @Override
    public String toString() {
        return "    " + this.getName() + "  ID: " + this.getName();
    }
}
