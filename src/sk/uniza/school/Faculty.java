package sk.uniza.school;

import sk.uniza.people.Teacher;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 3/29/2022 - 3:01 PM
 *
 * @author marek
 */
public class Faculty extends SchoolStructure {
    private HashMap<String, FieldOfStudy> fieldsList;
    private HashMap<String, Teacher> teachers;

    public Faculty(String id, String name) {
        super(id, name);
        this.fieldsList = new HashMap<>();
        this.teachers = new HashMap<>();
    }

    public FieldOfStudy getFieldOfStudy(String id) {
        return  this.fieldsList.get(id);
    }

    public HashMap<String, FieldOfStudy> getFieldsList() {
        HashMap<String, FieldOfStudy> fieldList = new HashMap<>();
        Iterator it = this.fieldsList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            fieldList.put((String)pair.getKey(), (FieldOfStudy)pair.getValue());
        }
        return fieldList;
    }

    public void addFieldOfStudy(FieldOfStudy field) {
        if (field != null) {
            if (!this.fieldsList.isEmpty()) {
                if (!this.fieldsList.containsKey(field.getId())) {
                    this.fieldsList.put(field.getId(), field);
                } else {
                    System.out.println("Odbor sa uz v zozname nachadza");
                }
            } else {
                this.fieldsList.put(field.getId(), field);
            }
        } else {
            System.out.println("Chyba! Zadajte spravny vstupny parameter");
        }
    }

    public void addTeacher(Teacher teacher) {
        if (teacher != null) {
            if (!this.teachers.isEmpty()) {
                if (!this.teachers.containsKey(teacher.getId())) {
                    this.teachers.put(teacher.getId(), teacher);
                } else {
                    System.out.println("Ucitel sa uz v zozname nachadza");
                }
            } else {
                this.teachers.put(teacher.getId(), teacher);
            }
        } else {
            System.out.println("Chyba! Pre úspešné pridanie známky z predmetu je potrebné zadať správne vstupné parametre");
        }
    }

    public void printFieldsOfStudy() {
        if (!this.fieldsList.isEmpty()) {
            Iterator it = this.fieldsList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                FieldOfStudy f1 = (FieldOfStudy)pair.getValue();
                System.out.println(f1.getId() + " " + f1.getName());
            }
        }
    }

    public void printTeachers() {
        if (!this.teachers.isEmpty()) {
            Iterator it = this.teachers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Teacher t1 = (Teacher)pair.getValue();
                System.out.println(t1.getId() + " " + t1.getFullName());
            }
        }
    }

    @Override
    public String toString() {
        return "Faculty{} " + getId() + " " + getName();
    }
}
