package sk.uniza.school;

import sk.uniza.fileHandling.ExportToFile;
import sk.uniza.people.Student;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 3/29/2022 - 3:01 PM
 *
 * @author marek
 */
public abstract class SchoolStructure extends ExportToFile {
    private String id;
    private String name;
    private HashMap<String, Student> students;

    public SchoolStructure(String id, String name) {
        this.id = id;
        this.name = name;
        this.students = new HashMap<String, Student>();
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
        Iterator it = this.students.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            studList.put((String)pair.getKey(), (Student)pair.getValue());
        }
        return studList;
    }
    public void addStudent(Student student) {
        if (student != null) {
            if (!this.students.isEmpty()) {
                if (!this.students.containsKey(student.getId())) {
                    this.students.put(student.getId(), student);
                } else {
                    System.out.println("Student sa uz v zozname nachadza");
                }
            } else {
                this.students.put(student.getId(), student);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    public void removeStudent(Student student) {
        if (student != null && !this.students.isEmpty()) {
            this.students.remove(student.getId());
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    public void printStudents() {
        for (Student stud : this.students.values()) {
            System.out.println(stud.toString());
        }
    }
}
