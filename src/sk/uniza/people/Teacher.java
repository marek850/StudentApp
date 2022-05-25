package sk.uniza.people;

import sk.uniza.school.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * 3/29/2022 - 3:01 PM
 * Trieda modeluje pouzivatela aplikacie Ucitel. Trieda je priamym potomkom abstraktnej triedy Person
 * @author marek
 */
public class Teacher extends Person {
    private String password;
    private final HashMap<String, Subject> teachingSubjects;

    /**
     * Vytvori instanciu triedy a nastavi hodnoty urcene vstupnymi parametrami atributom.
     * @param id - identifikator ucitela
     * @param name - krstne meno ucitela
     * @param surname - priezvisko ucitela
     * @param password - heslo na prihlasenie do systemu
     */
    public Teacher(String id, String name, String surname, String password) {
        super(id, name, surname);
        this.password = password;
        this.teachingSubjects = new HashMap<>();
    }

    public HashMap<String, Subject> getTeachingSubjects() {
        HashMap<String, Subject> subjects = new HashMap<>();
        for (Map.Entry<String, Subject> stringSubjectEntry : this.teachingSubjects.entrySet()) {
            subjects.put((String)((Map.Entry)stringSubjectEntry).getKey(), (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue());

        }
        return subjects;
    }

    /**
     * Prida novy predmet do zoznamu predmetov ucitela. Novy predmet je urceny vstupnym parametrom
     * @param subject novy predmet na pridanie
     */
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

    /**
     * Odstrani predmet zo zoznamu ucitelovych predmetov. Predmet je urceny vstupnym parametrom.
     * @param subject predmet na odstranenie zo zoznamu
     */
    public void removeSubject(Subject subject) {
        if (subject != null) {
            if (!this.teachingSubjects.isEmpty()) {
                this.teachingSubjects.remove(subject.getId());
            }
        }
    }

    @Override
    public String getLogin() {
        return super.getId();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void changePassword(String pass) {
        this.password = pass;
    }

    @Override
    public String toString() {
        return "    " + this.getFullName() + " \n ID:  " + this.getId();
    }
}
