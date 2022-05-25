package sk.uniza.school;

import sk.uniza.people.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * 3/29/2022 - 3:01 PM
 * Abstraktna trieda ktora zdruzuje spolocne vlastnosti jednotlivych casti struktury skoly ako su Fakulta, Odbor atd.
 * @author marek
 */
public abstract class SchoolStructure {
    private String id;
    private String name;
    private HashMap<String, Student> students;

    /**
     * Nastavi hodnoty urcene vstupnymi parametrami atributom
     * @param id identifikator
     * @param name nazov
     */
    public SchoolStructure(String id, String name) {
        this.id = id;
        this.name = name;
        this.students = new HashMap<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Student> getStudentsList() {
        HashMap<String, Student> studList = new HashMap<>();
        for (Map.Entry<String, Student> stringStudentEntry : this.students.entrySet()) {
            studList.put((String)((Map.Entry<?, ?>)stringStudentEntry).getKey(), (Student)((Map.Entry<?, ?>)stringStudentEntry).getValue());
        }
        return studList;
    }

    /**
     * Prida noveho studenta do zoznamu studentov.
     * @param student Novy student
     */
    public void addStudent(Student student) {
        if (student != null) {
            if (!this.students.isEmpty()) {
                if (!this.students.containsKey(student.getId())) {
                    this.students.put(student.getId(), student);
                }
            } else {
                this.students.put(student.getId(), student);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    /**
     * Odstrani studenta zo zoznamu studentov
     * @param student student na odstranenie
     */
    public void removeStudent(Student student) {
        if (student != null && !this.students.isEmpty()) {
            this.students.remove(student.getId());
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

}
