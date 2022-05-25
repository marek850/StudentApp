package sk.uniza.fileHandling;
import sk.uniza.people.Admin;
import sk.uniza.people.Student;
import sk.uniza.people.Teacher;
import sk.uniza.school.Faculty;
import sk.uniza.school.FieldOfStudy;
import sk.uniza.school.Group;
import sk.uniza.school.Subject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
/**
 * 4/3/2022 - 6:36 PM
 * Trieda riesi zapisovanie údajov do csv súboru
 * @author marek
 */
public class ExportToFile {
    /**
     * Vytvori instanciu triedy
     */
    public ExportToFile() {
    }
    /*
    !EXPORTOVANIE ZOZNAMOV
     */

    /**
     * Zapise vsetky odbory zo zoznamu do csv suboru.
     * Zoznam je dany parametrom fieldList.
     * Cesta k suboru a jeho nazov je urceny parametrom nameOfFile
     * @param fieldList zoznam odborov na zapisanie
     * @param nameOfFile cesta a názov suboru na zapísanie
     */
    public void exportFields(HashMap<String, FieldOfStudy> fieldList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');

            for (Map.Entry<String, FieldOfStudy> stringFieldOfStudyEntry : fieldList.entrySet()) {
                FieldOfStudy f = (FieldOfStudy)((Map.Entry<?, ?>)stringFieldOfStudyEntry).getValue();
                sb.append(f.getId());
                sb.append(',');
                sb.append(f.getName());
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Zapíše vsetky fakulty zo zoznamu do csv súboru. Zoznam a názov súboru je daný parametrami
     * @param facultyList zoznam fakúlt na zapísanie do súboru
     * @param nameOfFile cesta k súboru a jeho názov
     */
    public void exportFaculties(HashMap<String, Faculty> facultyList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');

            for (Map.Entry<String, Faculty> stringFacultyEntry : facultyList.entrySet()) {
                Faculty f = (Faculty)((Map.Entry<?, ?>)stringFacultyEntry).getValue();
                sb.append(f.getId());
                sb.append(',');
                sb.append(f.getName());
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Zapíše vsetky skupiny zo zoznamu do csv súboru. Zoznam a názov súboru je daný parametrami
     * @param groupList zoznam fakúlt na zapísanie do súboru
     * @param nameOfFile cesta k súboru a jeho názov
     */
    public void exportGroups(HashMap<String, Group> groupList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');

            for (Map.Entry<String, Group> stringGroupEntry : groupList.entrySet()) {
                Group g = (Group)((Map.Entry<?, ?>)stringGroupEntry).getValue();
                sb.append(g.getId());
                sb.append(',');
                sb.append(g.getName());
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Zapíše vsetky predmety zo zoznamu do csv súboru. Zoznam a názov súboru je daný parametrami
     * @param subjectList zoznam fakúlt na zapísanie do súboru
     * @param nameOfFile cesta k súboru a jeho názov
     */
    public void exportSubjects(HashMap<String, Subject> subjectList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Capacity");
            sb.append(',');
            sb.append("Name");
            sb.append(',');
            sb.append("Description");
            sb.append('\n');

            for (Map.Entry<String, Subject> stringSubjectEntry : subjectList.entrySet()) {
                Subject s = (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue();
                sb.append(s.getId());
                sb.append(',');
                sb.append(s.getCapacity());
                sb.append(',');
                sb.append(s.getName());
                sb.append(',');
                if (s.getDescription().isEmpty()) {
                    sb.append(" ");
                } else {
                    String desc = String.valueOf(s.getDescription()).replaceAll("[\r\n]+", "@");
                    sb.append(desc);
                    //sb.append(String.valueOf(s.getDescription()));
                }
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Zapíše vsetkych studentov zo zoznamu do csv súboru. Zoznam a názov súboru je daný parametrami
     * @param studentsList zoznam fakúlt na zapísanie do súboru
     * @param nameOfFile cesta k súboru a jeho názov
     */
    public void exportStudents(HashMap<String, Student> studentsList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Title infront");
            sb.append(',');
            sb.append("Name");
            sb.append(',');
            sb.append("Surname");
            sb.append(',');
            sb.append("Title after");
            sb.append(',');
            sb.append("Password");
            sb.append(',');
            sb.append("Year of study");
            sb.append(',');
            sb.append("Degree");
            sb.append('\n');

            for (Map.Entry<String, Student> stringStudentEntry : studentsList.entrySet()) {
                Student s = (Student)((Map.Entry<?, ?>)stringStudentEntry).getValue();
                sb.append(s.getId());
                sb.append(',');
                if (s.getTitleInFront() != null) {
                    sb.append(s.getTitleInFront());
                    sb.append(',');
                } else {
                    sb.append(" ");
                    sb.append(',');
                }
                sb.append(s.getName());
                sb.append(',');
                sb.append(s.getSurname());
                sb.append(',');
                if (s.getTitleBehind() != null) {
                    sb.append(s.getTitleBehind());
                    sb.append(',');
                } else {
                    sb.append(" ");
                    sb.append(',');
                }
                sb.append(s.getPassword());
                sb.append(',');
                sb.append(s.getYear());
                sb.append(',');
                sb.append(s.getDegree());
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Zapíše vsetkych ucitelov zo zoznamu do csv súboru. Zoznam a názov súboru je daný parametrami
     * @param teachersList zoznam fakúlt na zapísanie do súboru
     * @param nameOfFile cesta k súboru a jeho názov
     */
    public void exportTeachers(HashMap<String, Teacher> teachersList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Title infront");
            sb.append(',');
            sb.append("Name");
            sb.append(',');
            sb.append("Surname");
            sb.append(',');
            sb.append("Title after");
            sb.append(',');
            sb.append("Password");
            sb.append('\n');

            for (Map.Entry<String, Teacher> stringTeacherEntry : teachersList.entrySet()) {
                Teacher t = (Teacher)((Map.Entry<?, ?>)stringTeacherEntry).getValue();
                sb.append(t.getId());
                sb.append(',');
                if (t.getTitleInFront() != null) {
                    sb.append(t.getTitleInFront());
                    sb.append(',');
                } else {
                    sb.append(" ");
                    sb.append(',');
                }
                sb.append(t.getName());
                sb.append(',');
                sb.append(t.getSurname());
                sb.append(',');
                if (t.getTitleBehind() != null) {
                    sb.append(t.getTitleBehind());
                    sb.append(',');
                } else {
                    sb.append(" ");
                    sb.append(',');
                }
                sb.append(t.getPassword());
                sb.append(',');
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Zapíše vsetkych adminov zo zoznamu do csv súboru. Zoznam a názov súboru je daný parametrami
     * @param adminList zoznam fakúlt na zapísanie do súboru
     * @param nameOfFile cesta k súboru a jeho názov
     */
    public void exportAdmins(HashMap<String, Admin> adminList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Title infront");
            sb.append(',');
            sb.append("Name");
            sb.append(',');
            sb.append("Surname");
            sb.append(',');
            sb.append("Title after");
            sb.append(',');
            sb.append("Password");
            sb.append('\n');

            for (Map.Entry<String, Admin> stringAdminEntry : adminList.entrySet()) {
                Admin a = (Admin)((Map.Entry<?, ?>)stringAdminEntry).getValue();
                sb.append(a.getId());
                sb.append(',');
                if (a.getTitleInFront() != null) {
                    sb.append(a.getTitleInFront());
                    sb.append(',');
                } else {
                    sb.append(" ");
                    sb.append(',');
                }
                sb.append(a.getName());
                sb.append(',');
                sb.append(a.getSurname());
                sb.append(',');
                if (a.getTitleBehind() != null) {
                    sb.append(a.getTitleBehind());
                    sb.append(',');
                } else {
                    sb.append(" ");
                    sb.append(',');
                }
                sb.append(a.getPassword());
                sb.append(',');
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    EXPORTOVANIE ZOZNAMOV OBSAHUJÚCICH ROZDELENIA
     */

    /**
     * Zapisuje do csv suboru zo zoznamu studentov a predmety na ktore su prihlaseni
     * @param studentList zoznam studentov
     * @param nameOfFile cesta a nazov suboru na zapisanie
     */
    public void exportStudentAssignment(HashMap<String, Student> studentList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();

            sb.append("ID student");
            sb.append(',');
            sb.append("ID fakulta");
            sb.append(',');
            sb.append("ID odbor");
            sb.append(',');
            sb.append("ID skupina");
            sb.append(',');
            sb.append("ID predmet");
            sb.append('\n');

            /*
                Prechádza celý hasmap študentov. Pre každého študenta zapisuje udaje vo formáte
                ID študenta, ID fakulta, ID odbor, ID skupina, ID predmet
                           ,           ,         ,           , ID predmet
            */

            for (Map.Entry<String, Student> stringStudentEntry : studentList.entrySet()) {
                Student s = (Student)((Map.Entry<?, ?>)stringStudentEntry).getValue();
                Random generator = new Random();
                Object[] values = s.getSubjects().values().toArray();
                Subject randomSubject = null;
                String subjectID;
                if (values.length > 0) {
                    randomSubject = (Subject)values[generator.nextInt(values.length)];
                    subjectID = randomSubject.getId();
                } else {
                    subjectID = " ";
                }
                sb.append(s.getId());
                sb.append(',');
                sb.append(s.getFaculty().getId());
                sb.append(',');
                sb.append(s.getField().getId());
                sb.append(',');
                sb.append(s.getGroup().getId());
                sb.append(',');
                sb.append(subjectID);
                sb.append('\n');
                for (Map.Entry<String, Subject> stringSubjectEntry : s.getSubjects().entrySet()) {
                    Subject subject = (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue();
                    if (subject != randomSubject) {
                        sb.append(" ");
                        sb.append(',');
                        sb.append(" ");
                        sb.append(',');
                        sb.append(" ");
                        sb.append(',');
                        sb.append(" ");
                        sb.append(',');
                        sb.append(subject.getId());
                        sb.append('\n');
                    }
                }

            }
            writer.write(sb.toString());
            //System.out.println(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Zapisuje do csv suboru prideleneie odborov k fakultam
     * @param facultyList zoznam fakult
     * @param nameOfFile cesta a nazov suboru
     */
    public void exportFieldsAssignment(HashMap<String, Faculty> facultyList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();

            sb.append("ID fakulty");
            sb.append(',');
            sb.append("ID odboru na priradenie");
            sb.append('\n');

            /*
                Prechádza celý hasmap fakult. Pre každu fakultu zapisuje udaje vo formáte
                ID fakulty, ID odboru na pridanie
                          , ID odboru na pridanie
            */

            for (Map.Entry<String, Faculty> stringFacultyEntry : facultyList.entrySet()) {
                Faculty f = (Faculty)((Map.Entry<?, ?>)stringFacultyEntry).getValue();
                Random generator = new Random();
                Object[] values = f.getFieldsList().values().toArray();
                FieldOfStudy randomField = (FieldOfStudy)values[generator.nextInt(values.length)];

                sb.append(f.getId());
                sb.append(',');
                sb.append(randomField.getId());
                sb.append('\n');
                for (Map.Entry<String, FieldOfStudy> stringFieldOfStudyEntry : f.getFieldsList().entrySet()) {
                    FieldOfStudy field = (FieldOfStudy)((Map.Entry<?, ?>)stringFieldOfStudyEntry).getValue();
                    if (field != randomField) {
                        sb.append(" ");
                        sb.append(',');
                        sb.append(field.getId());
                        sb.append('\n');
                    }
                }

            }
            writer.write(sb.toString());
            //System.out.println(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Zapisuje do csv suboru rozdelenie studijnych skupin a predmetov medzi odbory
     * @param fieldList zoznam fakult
     * @param nameOfFile cesta a nazov suboru
     */
    public void exportGSAssignment(HashMap<String, FieldOfStudy> fieldList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();

            sb.append("ID odboru");
            sb.append(',');
            sb.append("ID predmetu");
            sb.append(',');
            sb.append("ID skupiny");
            sb.append('\n');

            /*
                Prechádza celý hasmap študentov. Pre každého študenta zapisuje udaje vo formáte
                ID študenta, ID fakulta, ID odbor, ID skupina, ID predmet
                           ,           ,         ,           , ID predmet
            */

            for (Map.Entry<String, FieldOfStudy> stringFieldOfStudyEntry : fieldList.entrySet()) {
                FieldOfStudy f = (FieldOfStudy)((Map.Entry<?, ?>)stringFieldOfStudyEntry).getValue();
                Random generator = new Random();
                Object[] values = f.getSubjects().values().toArray();
                Subject randomSubject = (Subject)values[generator.nextInt(values.length)];
                Object[] groupValues = f.getGroups().values().toArray();
                Group randomGroup = (Group)groupValues[generator.nextInt(groupValues.length)];

                sb.append(f.getId());
                sb.append(',');
                sb.append(randomSubject.getId());
                sb.append(',');
                sb.append(randomGroup.getId());
                sb.append('\n');

                Iterator iterator = f.getSubjects().entrySet().iterator();
                Iterator iter = f.getGroups().entrySet().iterator();
                while (iterator.hasNext() || iter.hasNext()) {
                    Group group = null;
                    Subject subject = null;
                    if (iter.hasNext()) {
                        Map.Entry groups = (Map.Entry)iter.next();
                        group = (Group)groups.getValue();
                    }
                    if (iterator.hasNext()) {
                        Map.Entry subjects = (Map.Entry)iterator.next();
                        subject = (Subject)subjects.getValue();
                    }

                    if (subject != randomSubject) {
                        sb.append(" ");
                        sb.append(',');
                        sb.append(Objects.requireNonNull(subject).getId());
                        sb.append(',');
                        if (group != randomGroup && group != null) {
                            sb.append(group.getId());
                        } else {
                            sb.append(" ");
                        }
                        sb.append('\n');
                    }
                }
            }
            writer.write(sb.toString());
            //System.out.println(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Zapisuje do csv suboru prideleneie predmetov vyucujucim ucitelom
     * @param teacherList zoznam ucitelov
     * @param nameOfFile cesta a nazov suboru
     */
    public void exportTeachersAssignment(HashMap<String, Teacher> teacherList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();

            sb.append("ID ucitela");
            sb.append(',');
            sb.append("ID predmetu");
            sb.append('\n');

            /*
                Prechádza celý hasmap študentov. Pre každého študenta zapisuje udaje vo formáte
                ID študenta, ID fakulta, ID odbor, ID skupina, ID predmet
                           ,           ,         ,           , ID predmet
            */

            for (Map.Entry<String, Teacher> stringTeacherEntry : teacherList.entrySet()) {
                Teacher t = (Teacher)((Map.Entry<?, ?>)stringTeacherEntry).getValue();
                Random generator = new Random();
                Object[] values = t.getTeachingSubjects().values().toArray();
                Subject randomSubject = (Subject)values[generator.nextInt(values.length)];

                sb.append(t.getId());
                sb.append(',');
                sb.append(randomSubject.getId());
                sb.append('\n');
                for (Map.Entry<String, Subject> stringSubjectEntry : t.getTeachingSubjects().entrySet()) {
                    Subject subject = (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue();
                    if (subject != randomSubject) {
                        sb.append(" ");
                        sb.append(',');
                        sb.append(subject.getId());
                        sb.append('\n');
                    }
                }
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

