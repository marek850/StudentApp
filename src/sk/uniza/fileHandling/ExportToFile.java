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
import java.util.Random;

/**
 * 4/3/2022 - 6:36 PM
 *
 * @author marek
 */
public class ExportToFile {
    public ExportToFile() {
    }
    /*
    !EXPORTOVANIE ZOZNAMOV
     */
    public void exportFields(HashMap<String, FieldOfStudy> fieldList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');

            Iterator it = fieldList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                FieldOfStudy f = (FieldOfStudy)pair.getValue();
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

    public void exportFaculties(HashMap<String, Faculty> facultyList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');

            Iterator it = facultyList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Faculty f = (Faculty)pair.getValue();
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

    public void exportGroups(HashMap<String, Group> groupList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');

            Iterator it = groupList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Group g = (Group)pair.getValue();
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

    public void exportSubjects(HashMap<String, Subject> subjectList, String nameOfFile) {
        try (PrintWriter writer = new PrintWriter(nameOfFile + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID");
            sb.append(',');
            sb.append("Name");
            sb.append('\n');

            Iterator it = subjectList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Subject s = (Subject)pair.getValue();
                sb.append(s.getId());
                sb.append(',');
                sb.append(s.getName());
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

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

            Iterator it = studentsList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Student s = (Student)pair.getValue();
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

            Iterator it = teachersList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Teacher t = (Teacher)pair.getValue();
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

            Iterator it = adminList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Admin a = (Admin)pair.getValue();
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
     * Zapisuje do csv súboru určeného parametrom nameOfFile pridelenie študijných skupín a predmetov študentom
     * @param studentList
     * @param nameOfFile
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

            Iterator it = studentList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Student s = (Student)pair.getValue();
                Random generator = new Random();
                Object[] values = s.getSubjects().values().toArray();
                Subject randomSubject = null;
                if (values.length > 0) {
                    randomSubject = (Subject)values[generator.nextInt(values.length)];
                }
                sb.append(s.getId());
                sb.append(',');
                sb.append(s.getFaculty().getId());
                sb.append(',');
                sb.append(s.getField().getId());
                sb.append(',');
                sb.append(s.getGroup().getId());
                sb.append(',');
                sb.append(randomSubject.getId());
                sb.append('\n');
                Iterator iterator = s.getSubjects().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry pairs = (Map.Entry)iterator.next();
                    Subject subject = (Subject)pairs.getValue();
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

            Iterator it = facultyList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Faculty f = (Faculty)pair.getValue();
                Random generator = new Random();
                Object[] values = f.getFieldsList().values().toArray();
                FieldOfStudy randomField = (FieldOfStudy)values[generator.nextInt(values.length)];

                sb.append(f.getId());
                sb.append(',');
                sb.append(randomField.getId());
                sb.append('\n');
                Iterator iterator = f.getFieldsList().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry pairs = (Map.Entry)iterator.next();
                    FieldOfStudy field = (FieldOfStudy)pairs.getValue();
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

            Iterator it = fieldList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                FieldOfStudy f = (FieldOfStudy)pair.getValue();
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
                        sb.append(subject.getId());
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

            Iterator it = teacherList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Teacher t = (Teacher)pair.getValue();
                Random generator = new Random();
                Object[] values = t.getTeachingSubjects().values().toArray();
                Subject randomSubject = (Subject)values[generator.nextInt(values.length)];

                sb.append(t.getId());
                sb.append(',');
                sb.append(randomSubject.getId());
                sb.append('\n');
                Iterator iterator = t.getTeachingSubjects().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry pairs = (Map.Entry)iterator.next();
                    Subject subject = (Subject)pairs.getValue();
                    if (subject != randomSubject) {
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
}

