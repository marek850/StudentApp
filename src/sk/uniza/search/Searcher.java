package sk.uniza.search;

import sk.uniza.people.IUser;
import sk.uniza.school.Faculty;
import sk.uniza.school.FieldOfStudy;
import sk.uniza.school.Group;
import sk.uniza.school.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda sluzi na vyhladavanie udajov v aplikacii
 * @author marek
 */
public class Searcher {
    /**
     * Vytvori instanciu triedy
     */
    public Searcher() {
    }

    /**
     * Prejde zoznam skupin zadany vstupnym parametrom. Hlada zhodu medzi retazcom zadanym v vstupnom parametri
     * a hladanym vyrazom(id alebo nazov skupiny).
     * @param searchPhrase hladany vyraz
     * @param list zoznam skupin na prehladanie
     * @return ak najde zhodu vrati danu skupinu, inak vrati null
     */
    public Group searchGroup(String searchPhrase, HashMap<String, Group> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            Iterator it = list.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Group group = (Group)pair.getValue();
                if (group.getName().equals(searchPhrase) || group.getId().equals(searchPhrase)) {
                    return group;
                }
            }
        }
        return null;
    }
    /**
     * Prejde zoznam uzivatelov zadany vstupnym parametrom. Hlada zhodu medzi retazcom zadanym v vstupnom parametri
     * a hladanym vyrazom(id alebo meno uzivatela).
     * @param searchPhrase hladany vyraz
     * @param list zoznam uzivatelov na prehladanie
     * @return ak najde zhodu vrati daneho pouzivatela, inak vrati null
     */
    public IUser searchUser(String searchPhrase, ArrayList<IUser> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            for (IUser user : list) {
                if (user.getFullName().equals(searchPhrase) || user.getLogin().equals(searchPhrase)) {
                    return user;
                }
            }
        }
        return null;

    }
    /**
     * Prejde zoznam fakult zadany vstupnym parametrom. Hlada zhodu medzi retazcom zadanym v vstupnom parametri
     * a hladanym vyrazom(id alebo nazov fakulty).
     * @param searchPhrase hladany vyraz
     * @param list zoznam skupin na prehladanie
     * @return ak najde zhodu vrati danu fakultu, inak vrati null
     */
    public Faculty searchFaculties(String searchPhrase, HashMap<String, Faculty> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            Iterator it = list.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Faculty faculty = (Faculty)pair.getValue();
                if (faculty.getName().equals(searchPhrase) || faculty.getId().equals(searchPhrase)) {
                    return faculty;
                }
            }
        }
        return null;
    }
    /**
     * Prejde zoznam odborov zadany vstupnym parametrom. Hlada zhodu medzi retazcom zadanym v vstupnom parametri
     * a hladanym vyrazom(id alebo nazov skupiny).
     * @param searchPhrase hladany vyraz
     * @param list zoznam odborov na prehladanie
     * @return ak najde zhodu vrati dany odbor, inak vrati null
     */
    public FieldOfStudy searchFields(String searchPhrase, HashMap<String, FieldOfStudy> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            Iterator it = list.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                FieldOfStudy field = (FieldOfStudy)pair.getValue();
                if (field.getName().equals(searchPhrase) || field.getId().equals(searchPhrase)) {
                    return field;
                }
            }
        }
        return null;
    }
    /**
     * Prejde zoznam predmetov zadany vstupnym parametrom. Hlada zhodu medzi retazcom zadanym v vstupnom parametri
     * a hladanym vyrazom(id alebo nazov skupiny).
     * @param searchPhrase hladany vyraz
     * @param list zoznam predmetov na prehladanie
     * @return ak najde zhodu vrati dany predmet, inak vrati null
     */
    public Subject searchSubjects(String searchPhrase, HashMap<String, Subject> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            Iterator it = list.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Subject subject = (Subject)pair.getValue();
                if (subject.getName().equals(searchPhrase) || subject.getId().equals(searchPhrase)) {
                    return subject;
                }
            }
        }
        return null;
    }
}
