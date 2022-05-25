package sk.uniza.school;

import sk.uniza.fileHandling.MyFilenameFilter;
import sk.uniza.people.Admin;
import sk.uniza.people.IUser;
import sk.uniza.people.Student;
import sk.uniza.people.Teacher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 4/3/2022 - 6:36 PM
 * Trieda modeluje vysoku skolu
 * @author marek
 */
public class School extends SchoolStructure {

    private final HashMap<String, Faculty> facultyList;
    private final HashMap<String, FieldOfStudy> fieldList;
    private final HashMap<String, Group>groupList;
    private final HashMap<String, Subject> subjectList;
    private final HashMap<String, Admin> adminList;
    private final HashMap<String, Teacher> teacherList;

    private final ArrayList<IUser> userList;

    /**
     * Vytvori instanciu triedy. Inicializuje atributy a nacita potrebne udaje z suborov.
     * Rozdeli potrebne sucasti na zaklade dat ziskanych z suborov.
     * @param id Identifikator skoly
     * @param name Nazov skoly
     */
    public School(String id, String name) {
        super(id, name);
        this.facultyList = new HashMap<>();
        this.fieldList = new HashMap<>();
        this.groupList = new HashMap<>();
        this.subjectList = new HashMap<>();
        this.userList = new ArrayList<>();
        this.adminList = new HashMap<>();
        this.teacherList = new HashMap<>();

        this.loadStudents("Students.csv");
        this.loadFaculties("Faculties.csv");
        this.loadFields("Fields.csv");
        this.loadGroups("Groups.csv");
        this.loadAdmins("Admins.csv");
        this.loadSubjects("Subjects.csv");
        this.loadTeachers("Teachers.csv");

        this.assignFields();
        this.fieldGrpSubAssign();
        this.assignSubjectsToTeachers();
        this.assignStudents();
    }

    /**
     * Nacita vyucovane predmety z csv suboru, ktoreho nazov je urceny vstupnym parametrom.
     * @param csvFile Nazov suboru v ktorom sa nachadza zoznam predmetov
     */
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
                    if (!tempArr[3].equals(" ")) {
                        s.addDescription(tempArr[3].replaceAll("@", "\n"));
                    }
                    this.addSubject(s);
                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }

    /**
     * Nacita ucitelov z csv suboru, ktoreho nazov je urceny vstupnym parametrom.
     * @param csvFile Nazov suboru v ktorom sa nachadza zoznam ucitelov
     */
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
                    this.addTeacher(t);
                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    }
    /**
     * Nacita zoznam adminov z csv suboru, ktoreho nazov je urceny vstupnym parametrom.
     * @param csvFile Nazov suboru v ktorom sa nachadza zoznam adminov
     */
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
                    this.addAdmin(a);
                }
                br.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * Prida noveho admina do zoznamu adminov skoly. Novy admin je urceny vstupnym parametrom.
     * @param admin Novy admin na pridanie do zoznamu.
     */
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
    /**
     * Prida noveho ucitela do zoznamu ucitelov skoly. Novy ucitel je urceny vstupnym parametrom.
     * @param teacher Novy ucitel na pridanie do zoznamu.
     */
    public void addTeacher(Teacher teacher) {
        if (teacher != null) {
            if (!this.teacherList.isEmpty()) {
                if (!this.teacherList.containsKey(teacher.getId())) {
                    this.teacherList.put(teacher.getId(), teacher);
                    this.userList.add(teacher);
                } else {
                    System.out.println("Ucitel sa uz v zozname nachadza");
                }
            } else {
                this.teacherList.put(teacher.getId(), teacher);
                this.userList.add(teacher);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }
    /**
     * Prida novy predmet do zoznamu predmetov skoly. Novy predmet je urceny vstupnym parametrom.
     * @param subject Novy predmet na pridanie do zoznamu.
     */
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
    /**
     * Nacita zoznam studentov z csv suboru, ktoreho nazov je urceny vstupnym parametrom.
     * @param csvFile Nazov suboru v ktorom sa nachadza zoznam studentov
     */
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
    /**
     * Nacita zoznam studijnych skupin z csv suboru, ktoreho nazov je urceny vstupnym parametrom.
     * @param csvFile Nazov suboru v ktorom sa nachadza zoznam studijnych skupin
     */
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
    /**
     * Prida novu skupinu do zoznamu studijnych skupin  skoly. Nova studijna skupina je urcena vstupnym parametrom.
     * @param group Nova studijna skupina na pridanie do zoznamu.
     */
    private void addGroup(Group group) {
        if (group != null) {
            if (!this.groupList.isEmpty()) {
                if (!this.groupList.containsKey(group.getId())) {
                    this.groupList.put(group.getId(), group);
                }
            } else {
                this.groupList.put(group.getId(), group);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }
    /**
     * Nacita fakulty z csv suboru, ktoreho nazov je urceny vstupnym parametrom.
     * @param csvFile Nazov suboru v ktorom sa nachadza zoznam fakult
     */
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
    /**
     * Prida novu fakultu do zoznamu fakult skoly. Nova fakulta je urcena vstupnym parametrom.
     * @param faculty Nova fakulta na pridanie do zoznamu.
     */
    public void addFaculty(Faculty faculty) {
        if (faculty != null) {
            if (!this.facultyList.isEmpty()) {
                if (!this.facultyList.containsKey(faculty.getId())) {
                    this.facultyList.put(faculty.getId(), faculty);
                }
            } else {
                this.facultyList.put(faculty.getId(), faculty);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }
    /**
     * Nacita studijne odbory z csv suboru, ktoreho nazov je urceny vstupnym parametrom.
     * @param csvFile Nazov suboru v ktorom sa nachadza zoznam odborov
     */
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
    /**
     * Prida novy odbor do zoznamu odborov skoly. Novy odbor je urceny vstupnym parametrom.
     * @param fieldOfStudy Novy odbor na pridanie do zoznamu.
     */
    private void addField(FieldOfStudy fieldOfStudy) {
        if (fieldOfStudy != null) {
            if (!this.fieldList.isEmpty()) {
                if (!this.fieldList.containsKey(fieldOfStudy.getId())) {
                    this.fieldList.put(fieldOfStudy.getId(), fieldOfStudy);
                }
            } else {
                this.fieldList.put(fieldOfStudy.getId(), fieldOfStudy);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    /**
     * Prida noveho pouzivatela aplikacie do zoznamu pouzivatelov. Novy pouzivatel je urceny vstupnym parametrom
     * @param newUser Novy pouzivatel aplikacie
     */
    public void addUser(IUser newUser) {
        if (newUser != null) {
            if (!this.userList.isEmpty()) {
                if (!this.userList.contains(newUser)) {
                    this.userList.add(newUser);
                }
            } else {
                this.userList.add(newUser);
            }
        } else {
            System.out.println("Chyba! Zadajte spravne vstupne parametre");
        }
    }

    /**
     * Overuje zhodu medzi zadanymi prihlasovacimi udajmi a udajmi existujucich pouzivatelov.
     * Zadane prihlasovacie udaje su urcene vstupnymi parametrami
     * @param login Prihlasovacie ID
     * @param pass Heslo na prihlasenie
     * @return Ak existuje zhoda medzi prihlasovacimi udajmi existujuceho pouzivatela a zadanymi udajmi,
     * metoda vrati daneho uzivatela, inak vrati null
     */
    public IUser loginAuthentification(String login, String pass) {
        for (IUser user : this.userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(pass)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Nacita udaje z csv suboru a nasledne na zaklade tychto udajov priradi studentom fakultu, odbor,
     * studijnu skupinu a predmety na ktore su prihlaseni.
     */
    private void assignStudents() {
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

    /**
     * Nacita data z csv suboru a nasledne na zaklade tychto dat priradi studijnym odborom studijne
     * skupiny a predmety vyucovane v danom odbore.
     */
    private void fieldGrpSubAssign() {
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

    /**
     * Nacita data z csv suboru a nasledne na zaklade tychto dat prideli fakultam studijne odbory
     */
    private void assignFields() {
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

    /**
     * Nacita data z csv suboru a nasledne na zaklade tychto dat prideli ucitelom predmety ktore vyucuju.
     */
    private void assignSubjectsToTeachers() {
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
    public ArrayList<IUser> getUserList() {
        ArrayList<IUser> users = new ArrayList<>();
        users.addAll(this.userList);
        return users;
    }


    public HashMap<String, Group> getGroupList() {
        HashMap<String, Group> groups = new HashMap<>();
        for (Map.Entry<String, Group> stringGroupEntry : this.groupList.entrySet()) {
            groups.put((String)((Map.Entry<?, ?>)stringGroupEntry).getKey(), (Group)((Map.Entry<?, ?>)stringGroupEntry).getValue());
        }
        return groups;
    }

    public HashMap<String, Faculty> getFacultyList() {
        HashMap<String, Faculty> faculty = new HashMap<>();
        for (Map.Entry<String, Faculty> stringFacultyEntry : this.facultyList.entrySet()) {
            faculty.put((String)((Map.Entry<?, ?>)stringFacultyEntry).getKey(), (Faculty)((Map.Entry<?, ?>)stringFacultyEntry).getValue());
        }
        return faculty;
    }

    public HashMap<String, FieldOfStudy> getFieldList() {
        HashMap<String, FieldOfStudy> fields = new HashMap<>();
        for (Map.Entry<String, FieldOfStudy> stringFieldOfStudyEntry : this.fieldList.entrySet()) {
            fields.put((String)((Map.Entry<?, ?>)stringFieldOfStudyEntry).getKey(), (FieldOfStudy)((Map.Entry<?, ?>)stringFieldOfStudyEntry).getValue());
        }
        return fields;
    }

    public HashMap<String, Teacher> getTeachersList() {
        HashMap<String, Teacher> teachers = new HashMap<>();
        for (Map.Entry<String, Teacher> stringTeacherEntry : this.teacherList.entrySet()) {
            teachers.put((String)((Map.Entry<?, ?>)stringTeacherEntry).getKey(), (Teacher)((Map.Entry<?, ?>)stringTeacherEntry).getValue());
        }
        return teachers;
    }

    public HashMap<String, Admin> getAdminList() {
        HashMap<String, Admin> admins = new HashMap<>();
        for (Map.Entry<String, Admin> stringAdminEntry : this.adminList.entrySet()) {
            admins.put((String)((Map.Entry<?, ?>)stringAdminEntry).getKey(), (Admin)((Map.Entry<?, ?>)stringAdminEntry).getValue());
        }
        return admins;
    }

    public HashMap<String, Subject> getSubjectList() {
        HashMap<String, Subject> subjects = new HashMap<>();
        for (Map.Entry<String, Subject> stringSubjectEntry : this.subjectList.entrySet()) {
            subjects.put((String)((Map.Entry<?, ?>)stringSubjectEntry).getKey(), (Subject)((Map.Entry<?, ?>)stringSubjectEntry).getValue());
        }
        return subjects;
    }

}
