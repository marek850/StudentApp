package sk.uniza;

import sk.uniza.people.Admin;
import sk.uniza.people.Student;
import sk.uniza.people.Teacher;
import sk.uniza.school.Faculty;
import sk.uniza.school.FieldOfStudy;
import sk.uniza.school.Group;
import sk.uniza.school.Subject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 4/3/2022 - 6:36 PM
 *
 * @author marek
 */
public class Search {

    public Search() {
    }

    public Group searchGroup(String searchPhrase, HashMap<String, Group> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            if (list.containsKey(searchPhrase)) {
                return list.get(searchPhrase);

            }
        }
        return null;
    }

    public Student searchStudents(String searchPhrase, HashMap<String, Student> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            if (list.containsKey(searchPhrase)) {
                return list.get(searchPhrase);
            }
        }
        return null;
    }

    public Student searchStudents(String name, String lastName, HashMap<String, Student> list) {
        Iterator it = list.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Student student = (Student)pair.getValue();
            if (student.getFullName().equals(name + " " + lastName)) {
                return student;
            }
        }
        return null;
    }

    public Faculty searchFaculties(String searchPhrase, HashMap<String, Faculty> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            if (list.containsKey(searchPhrase)) {
                return list.get(searchPhrase);

            }
        }
        return null;
    }

    public FieldOfStudy searchFields(String searchPhrase, HashMap<String, FieldOfStudy> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            if (list.containsKey(searchPhrase)) {
                return list.get(searchPhrase);

            }
        }
        return null;
    }

    public Subject searchSubjects(String searchPhrase, HashMap<String, Subject> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            if (list.containsKey(searchPhrase)) {
                return list.get(searchPhrase);

            }
        }
        return null;
    }

    public Teacher searchTeacher(String searchPhrase, HashMap<String, Teacher> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            if (list.containsKey(searchPhrase)) {
                return list.get(searchPhrase);

            }
        }
        return null;
    }

    public Admin searchAdmins(String searchPhrase, HashMap<String, Admin> list) {
        if (searchPhrase != null && !list.isEmpty()) {
            if (list.containsKey(searchPhrase)) {
                return list.get(searchPhrase);

            }
        }
        return null;
    }
}
