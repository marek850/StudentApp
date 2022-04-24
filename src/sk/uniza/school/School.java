package sk.uniza.school;

import sk.uniza.fileHandling.MyFilenameFilter;
import sk.uniza.people.Admin;
import sk.uniza.people.IUser;
import sk.uniza.people.Student;
import sk.uniza.people.Teacher;

import java.io.*;
import java.util.*;

/**
 * 4/3/2022 - 6:36 PM
 *
 * @author marek
 */
public class School extends SchoolStructure {

    private HashMap<String, Faculty> facultyList;
    private HashMap<String, FieldOfStudy> fieldList;
    private HashMap<String, Group>groupList;
    private HashMap<String, Subject> subjectList;
    private HashMap<String, Admin> adminList;
    private HashMap<String, Teacher> teacherList;

    private ArrayList<IUser> userList;

    public School(String id, String name) {
        super(id, name);
        this.facultyList = new HashMap<>();
        this.fieldList = new HashMap<>();
        this.groupList = new HashMap<>();
        this.subjectList = new HashMap<>();
        this.userList = new ArrayList<>();
        this.adminList = new HashMap<>();
        this.teacherList = new HashMap<>();

        loadStudents("Students.csv");
        loadFaculties("Faculties.csv");
        loadFields("Fields.csv");
        loadGroups("Groups.csv");
        loadAdmins("Admins.csv");
        loadSubjects("Subjects.csv");
        loadTeachers("Teachers.csv");

        assignFields();
        fieldGrpSubAssign();
        assignSubjectsToTeachers();
        assignStudents();
    }

    public void testLoad() {
        File directory = new File("ImportData/Assignments/Studenti");
        MyFilenameFilter filter = new MyFilenameFilter("rozdelenie.csv");
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor " + "test.csv" + " neexistuje");
        } else {
            try {
                File file = new File(directory + "/" + "rozdelenie.csv");
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                String[] tempArr;
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    System.out.println(tempArr.length);
                    for (int i = 0; i < tempArr.length;i++) {
                        if (tempArr[i].equals(" ")) {
                            System.out.print("medzera");
                        }
                        System.out.print(tempArr[i] + " ");
                    }
                    System.out.println();
                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    public void loadSubjects(String csvFile) {
        File directory = new File("ImportData");
        MyFilenameFilter filter = new MyFilenameFilter(csvFile);
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor " + csvFile + " neexistuje");
        } else {
            try {
                File file = new File(directory + "/" + csvFile);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                String line;
                String[] tempArr;
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    Subject s = new Subject(tempArr[0], tempArr[2], Integer.parseInt(tempArr[1]));
                    addSubject(s);
                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }

    public void loadTeachers(String csvFile) {
        File directory = new File("ImportData");
        MyFilenameFilter filter = new MyFilenameFilter(csvFile);
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor " + csvFile + " neexistuje");
        } else {
            try {
                File file = new File(directory + "/" + csvFile);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                String line;
                String[] tempArr;
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    Teacher t = new Teacher(tempArr[0], tempArr[2], tempArr[3], tempArr[5]);
                    if (tempArr[1] != null) {
                        t.setTitleInFront(tempArr[1]);
                    }
                    if (tempArr[4] != null) {
                        t.setTitleBehind(tempArr[4]);
                    }
                    addTeacher(t);
                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }

    public void loadAdmins(String csvFile) {
        File directory = new File("ImportData");
        MyFilenameFilter filter = new MyFilenameFilter(csvFile);
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor " + csvFile + " neexistuje");
        } else {
            try {
                File file = new File(directory + "/" + csvFile);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                String line;
                String[] tempArr;
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    Admin a = new Admin(tempArr[0], tempArr[2], tempArr[3], tempArr[5]);
                    if (tempArr[1] != null) {
                        a.setTitleInFront(tempArr[1]);
                    }
                    if (tempArr[4] != null) {
                        a.setTitleBehind(tempArr[4]);
                    }
                    addAdmin(a);
                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void addAdmin(Admin admin) {
        if (admin != null) {
            if (!this.adminList.isEmpty()) {
                if (!this.adminList.containsKey(admin.getId())) {
                    this.adminList.put(admin.getId(), admin);
                    this.userList.add(admin);
                } else {
                    System.out.println("Admin " + admin.getFullName() + " " + admin.getSurname() + " sa uz v zozname nachadza");
                }
            } else {
                this.adminList.put(admin.getId(), admin);
                this.userList.add(admin);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    public void addTeacher(Teacher teacher) {
        if (teacher != null) {
            if (!this.teacherList.isEmpty()) {
                if (!this.teacherList.containsKey(teacher.getId())) {
                    this.teacherList.put(teacher.getId(), teacher);
                } else {
                    System.out.println("Ucitel sa uz v zozname nachadza");
                }
            } else {
                this.teacherList.put(teacher.getId(), teacher);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    public void addSubject(Subject subject) {
        if (subject != null) {
            if (!this.subjectList.isEmpty()) {
                if (!this.subjectList.containsKey(subject.getId())) {
                    this.subjectList.put(subject.getId(), subject);
                } else {
                    System.out.println("Predmet sa uz v zozname nachadza");
                }
            } else {
                this.subjectList.put(subject.getId(), subject);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }
    public void loadStudents(String csvFile) {
        File directory = new File("ImportData");
        MyFilenameFilter filter = new MyFilenameFilter(csvFile);
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor " + csvFile + " neexistuje");
        } else {
            try {
                File file = new File(directory + "/" + csvFile);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                String line;
                String[] tempArr;
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    Student s = new Student(tempArr[0], tempArr[2], tempArr[3], tempArr[5], Integer.parseInt(tempArr[6]), tempArr[7]);
                    s.setTitleInFront(tempArr[1]);
                    s.setTitleBehind(tempArr[4]);
                    super.addStudent(s);
                    this.addUser(s);

                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void loadGroups(String csvFile) {
        File directory = new File("ImportData");
        MyFilenameFilter filter = new MyFilenameFilter(csvFile);
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor " + csvFile + " neexistuje");
        } else {
            try {
                File file = new File(directory + "/" + csvFile);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                String line;
                String[] tempArr;
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    this.addGroup(new Group(tempArr[0], tempArr[1]));
                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private void addGroup(Group group) {
        if (group != null) {
            if (!this.groupList.isEmpty()) {
                if (!this.groupList.containsKey(group.getId())) {
                    this.groupList.put(group.getId(), group);
                } else {
                    System.out.println("Student sa uz v zozname nachadza");
                }
            } else {
                this.groupList.put(group.getId(), group);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    public void loadFaculties(String csvFile) {
        File directory = new File("ImportData");
        MyFilenameFilter filter = new MyFilenameFilter(csvFile);
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor " + csvFile + " neexistuje");
        } else {
            try {
                File file = new File(directory + "/" + csvFile);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                String line;
                String[] tempArr;
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    this.addFaculty(new Faculty(tempArr[0], tempArr[1]));

                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void addFaculty(Faculty faculty) {
        if (faculty != null) {
            if (!this.facultyList.isEmpty()) {
                if (!this.facultyList.containsKey(faculty.getId())) {
                    this.facultyList.put(faculty.getId(), faculty);
                } else {
                    System.out.println("Student sa uz v zozname nachadza");
                }
            } else {
                this.facultyList.put(faculty.getId(), faculty);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }
    public void loadFields(String csvFile) {
        File directory = new File("ImportData");
        MyFilenameFilter filter = new MyFilenameFilter(csvFile);
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor " + csvFile + " neexistuje");
        } else {
            try {
                File file = new File(directory + "/" + csvFile);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                String line;
                String[] tempArr;
                //int riadok = 0;
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    //if (riadok > 0) {
                    this.addField(new FieldOfStudy(tempArr[0], tempArr[1]));
                    // }
                    //riadok++;
                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private void addField(FieldOfStudy fieldOfStudy) {
        if (fieldOfStudy != null) {
            if (!this.fieldList.isEmpty()) {
                if (!this.fieldList.containsKey(fieldOfStudy.getId())) {
                    this.fieldList.put(fieldOfStudy.getId(), fieldOfStudy);
                } else {
                    System.out.println("Student sa uz v zozname nachadza");
                }
            } else {
                this.fieldList.put(fieldOfStudy.getId(), fieldOfStudy);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    public void addUser(IUser newUser) {
        if (newUser != null) {
            if (!this.userList.isEmpty()) {
                if (!this.userList.contains(newUser)) {
                    this.userList.add(newUser);
                } else {
                    System.out.println("Student sa uz v zozname nachadza");
                }
            } else {
                this.userList.add(newUser);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    public void removeUser(IUser user) {
        if (user != null) {
            if (!this.userList.isEmpty()) {
                if (!this.userList.contains(user)) {
                    this.userList.remove(user);
                } else {
                    System.out.println("Student sa uz v zozname nachadza");
                }
            } else {
                this.userList.remove(user);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    public IUser loginAuthentification(String login, String pass) {
        for (IUser user : this.userList) {
            if (user instanceof Student) {
                Student s = (Student)user;
                if (s.getId().equals(login) && s.getPassword().equals(pass)) {
                    return s;
                }
            } else if (user instanceof Teacher) {
                Teacher t = (Teacher)user;
                if (t.getId().equals(login) && t.getPassword().equals(pass)) {
                    return t;
                }
            } else if (user instanceof Admin) {
                Admin a = (Admin)user;
                if (a.getId().equals(login) && a.getPassword().equals(pass)) {
                    return a;
                }
            }
        }
        return null;
    }

    public void printFaculties() {
        for (Faculty faculty : this.facultyList.values()) {
            System.out.println(faculty.toString());
            System.out.println(" %%%%% ");
            faculty.printFieldsOfStudy();
        }
    }

    public void printFields() {
        for (FieldOfStudy field : this.fieldList.values()) {
            System.out.println(field.toString());
            System.out.println( " ^^^^^^^^^^ Groups");
            field.printGroups();
            System.out.println(" $$$$$$$$$$Subjects");
            field.printSubjects();
        }
    }

    public void printGroups() {
        for (Group group : this.groupList.values()) {
            System.out.println(group.toString());
        }
    }

    public void printSubjects() {
        for (Subject subject : this.subjectList.values()) {
            System.out.println(subject.toString());
        }
    }

    public void printTeachers() {
        for ( Teacher teacher : this.teacherList.values()) {
            System.out.println(teacher.toString());
            System.out.println(" Predmety: ");
            teacher.printSubjects();
        }
    }

    public void printAdmins() {
        for (Admin admin : this.adminList.values()) {
            System.out.println(admin.toString());
        }
    }

    public void assignStudents() {
        File directory = new File("ImportData/Assignments/Studenti");
        MyFilenameFilter filter = new MyFilenameFilter( "rozdelenie.csv");
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor neexistuje");
        } else {

            try {
                BufferedReader reader = new BufferedReader(new FileReader(directory.getPath() + "/" + "rozdelenie.csv"));
                reader.readLine(); // this will read the first line
                String line1;
                String[] tempArr;
                String studentID = null;
                String subjectID = null;
                String fieldID = null;
                String facultyID = null;
                String groupID = null;
                while ((line1 = reader.readLine()) != null) {
                    tempArr = line1.split(",");
                    if (!tempArr[0].equals(" ")) {
                        studentID = tempArr[0];
                    }
                    if (!tempArr[1].equals(" ")) {
                        facultyID = tempArr[1];
                    }
                    if (!tempArr[2].equals(" ")) {
                        fieldID = tempArr[2];
                    }
                    if (!tempArr[3].equals(" ")) {
                        groupID = tempArr[3];
                    }
                    if (!tempArr[4].equals(" ")) {
                        subjectID = tempArr[4];
                    }


                    if (this.getStudentsList().containsKey(studentID)) {
                        Student student = this.getStudentsList().get(studentID);
                        if (this.facultyList.containsKey(facultyID)) {
                            student.setFaculty(this.facultyList.get(facultyID));
                        } else {
                            System.out.println("Fakulta sa nenachadza v databaze");
                        }
                        if (this.fieldList.containsKey(fieldID)) {
                            student.setField(this.fieldList.get(fieldID));
                        } else {
                            System.out.println("Odbor sa nenachadza v databaze");
                        }
                        if (this.groupList.containsKey(groupID)) {
                            student.setGroup(this.groupList.get(groupID));
                        } else {
                            System.out.println("Skupina sa nenachadza v databaze");
                        }
                        if (this.subjectList.containsKey(subjectID)) {
                            student.addSubject(this.subjectList.get(subjectID));
                        } else {
                            System.out.println("Predmet sa nenachadza v databaze");
                        }
                    } else {
                        System.out.println("Odbor sa nenachadza v databaze");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void fieldGrpSubAssign() {
        File directory = new File("ImportData/Assignments/Odbory");
        MyFilenameFilter filter = new MyFilenameFilter( "rozdelenieSkupinAPredmetov.csv");
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor neexistuje");
        } else {

            try {
                BufferedReader reader = new BufferedReader(new FileReader(directory.getPath() + "/" + "rozdelenieSkupinAPredmetov.csv"));
                reader.readLine(); // this will read the first line
                String line1;
                String[] tempArr;
                String groupID = null;
                String subjectID = null;
                String fieldID = null;
                while ((line1 = reader.readLine()) != null) {
                    tempArr = line1.split(",");
                    if (!tempArr[0].equals(" ")) {
                        fieldID = tempArr[0];
                    }
                    if (!tempArr[1].equals(" ")) {
                        subjectID = tempArr[1];
                    }
                    if (!tempArr[2].equals(" ")) {
                        groupID = tempArr[2];
                    }
                    if (this.fieldList.containsKey(fieldID)) {
                        FieldOfStudy field = this.fieldList.get(fieldID);
                        if (this.groupList.containsKey(groupID)) {
                            field.addGroup(this.groupList.get(groupID));
                        } else {
                            System.out.println("Skupina sa nenachadza v databaze");
                        }
                        if (this.subjectList.containsKey(subjectID)) {
                            field.addSubject(this.subjectList.get(subjectID));
                        } else {
                            System.out.println("Predmet sa nenachadza v databaze");
                        }

                    } else {
                        System.out.println("Odbor sa nenachadza v databaze");
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void assignFields() {
        File directory = new File("ImportData/Assignments/Fakulty");
        MyFilenameFilter filter = new MyFilenameFilter("pridelenieOdborov.csv");
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor neexistuje");
        } else {

            try {
                BufferedReader reader = new BufferedReader(new FileReader(directory.getPath() + "/" + "pridelenieOdborov.csv"));
                reader.readLine(); // this will read the first line
                String line1;
                String[] tempArr;
                String facultyID = null;
                String fieldID = null;
                while ((line1 = reader.readLine()) != null) {
                    tempArr = line1.split(",");
                    if (!tempArr[0].equals(" ")) {
                        facultyID = tempArr[0];
                    }
                    if (!tempArr[1].equals(" ")) {
                        fieldID = tempArr[1];
                    }
                    if (this.facultyList.containsKey(facultyID)) {
                        Faculty faculty = this.facultyList.get(facultyID);
                        if (this.fieldList.containsKey(fieldID)) {
                            faculty.addFieldOfStudy(this.fieldList.get(fieldID));
                        } else {
                            System.out.println("Dany odbor nie je v nasej databaze");
                        }
                    } else {
                        System.out.println("Pozadovana fakulta sa nenachadza v nasej databaze");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void assignSubjectsToTeachers() {
        File directory = new File("ImportData/Assignments/Ucitelia");

        MyFilenameFilter filter = new MyFilenameFilter("rozdeleniePredmetov.csv");
        String[] flist = directory.list(filter);
        if (flist.length == 0) {
            System.out.println("Subor neexistuje");
        } else {

            try {
                BufferedReader reader = new BufferedReader(new FileReader(directory.getPath() + "/" + "rozdeleniePredmetov.csv"));
                reader.readLine(); // this will read the first line
                String line1;
                String[] tempArr;
                String teacherID = null;
                String subjectID = null;
                while ((line1 = reader.readLine()) != null) {
                    tempArr = line1.split(",");
                    if (!tempArr[0].equals(" ")) {
                        teacherID = tempArr[0];
                    }
                    if (!tempArr[1].equals(" ")) {
                        subjectID = tempArr[1];
                    }
                    if (this.teacherList.containsKey(teacherID)) {
                        Teacher teacher = this.teacherList.get(teacherID);
                        if (this.subjectList.containsKey(subjectID)) {
                            teacher.addSubject(this.subjectList.get(subjectID));
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public HashMap<String, Group> getGroupList() {
        HashMap<String, Group> groups = new HashMap<>();
        Iterator it = this.groupList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            groups.put((String)pair.getKey(), (Group)pair.getValue());
        }
        return groups;
    }

    public HashMap<String, Faculty> getFacultyList() {
        HashMap<String, Faculty> faculty = new HashMap<>();
        Iterator it = this.facultyList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            faculty.put((String)pair.getKey(), (Faculty)pair.getValue());
        }
        return faculty;
    }

    public HashMap<String, FieldOfStudy> getFieldList() {
        HashMap<String, FieldOfStudy> fields = new HashMap<>();
        Iterator it = this.fieldList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            fields.put((String)pair.getKey(), (FieldOfStudy)pair.getValue());
        }
        return fields;
    }

    public HashMap<String, Teacher> getTeacherspList() {
        HashMap<String, Teacher> teachers = new HashMap<>();
        Iterator it = this.teacherList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            teachers.put((String)pair.getKey(), (Teacher)pair.getValue());
        }
        return teachers;
    }

    public HashMap<String, Admin> getAdminList() {
        HashMap<String, Admin> admins = new HashMap<>();
        Iterator it = this.adminList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            admins.put((String)pair.getKey(), (Admin)pair.getValue());
        }
        return admins;
    }

    public HashMap<String, Subject> getSubjectList() {
        HashMap<String, Subject> subjects = new HashMap<>();
        Iterator it = this.subjectList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            subjects.put((String)pair.getKey(), (Subject)pair.getValue());
        }
        return subjects;
    }

}
