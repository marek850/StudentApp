package sk.uniza;

import sk.uniza.fileHandling.ExportToFile;
import sk.uniza.people.Admin;
import sk.uniza.people.IUser;
import sk.uniza.people.Student;
import sk.uniza.people.Teacher;
import sk.uniza.school.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 4/3/2022 - 6:36 PM
 *
 * @author marek
 */

public class UserInterface {
    private static UserInterface instance;
    private String login;
    private String pass;
    private Scanner input;
    private IUser loggedUser;
    private School school;
    private Generate generator = new Generate();
    private Student loggedStudent;
    private Teacher loggedTeacher;
    private Admin loggedAdmin;
    private Search finder;

    protected UserInterface() {
        this.input = new Scanner(System.in);
        this.school = new School("5AS621", "UNIZA");
        this.finder = new Search();
        this.mainPage();
    }

    public static UserInterface getInstance() {
        if (UserInterface.instance == null) {
            UserInterface.instance = new UserInterface();
        }
        return UserInterface.instance;
    }
    private void menuNav(String choice) {
        int vyber = 0;
        switch (choice) {
            case "main":
                this.mainPage();
                break;
            case "login":
                this.loginForm();
                break;
            case "admin":
                this.clearTerminal();
                System.out.println("Menu:");
                System.out.println("[1] Pridať študenta");
                System.out.println("[2] Zmena Hesla");
                System.out.println("[3] Vyhľadať študenta/študentov");
                System.out.println("[4] Zobraziť zoznam fakult");
                System.out.println("[5] Zobraziť zoznam odborov");
                System.out.println("[6] Zobraziť zoznam skupin");
                System.out.println("[7] Zobraziť zoznam predmetov");
                System.out.println("[8] Odhlásiť");
                System.out.println("===================================");
                System.out.println("Zadaj cislo želanej možnosti");
                vyber = this.input.nextInt();
                switch (vyber) {
                    case 1:
                        String meno;
                        String priezvisko;
                        int rocnik;
                        String stupen;
                        System.out.println("Meno študenta:");
                        meno = this.input.next();
                        System.out.println("Priezvisko študenta:");
                        priezvisko = this.input.next();
                        System.out.println("Rocnik štúdia:");
                        rocnik = this.input.nextInt();
                        System.out.println("Stupeň štúdia (bc/ing):");
                        stupen = this.input.next();
                        System.out.println("Zoznam fakult: ");
                        Iterator it = this.school.getFacultyList().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            Faculty f = (Faculty)pair.getValue();
                            System.out.println(f.getId() + " " + f.getName());
                        }
                        System.out.println("ID fakulty študenta: ");
                        Faculty faculty = this.school.getFacultyList().get(this.input.next());
                        System.out.println("Zoznam odborov: ");
                        it = faculty.getFieldsList().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            FieldOfStudy field = (FieldOfStudy)pair.getValue();
                            System.out.println(field.getId() + " " + field.getName());
                        }
                        System.out.println("ID Odboru študenta: ");
                        FieldOfStudy field = faculty.getFieldOfStudy(this.input.next());
                        Student newStudent = new Student(this.generator.generateString(6), meno, priezvisko, this.generator.generateString(8), rocnik, stupen);
                        newStudent.setFaculty(faculty);
                        newStudent.setField(field);
                        //!Dorobit priradenie studenta na fakultu atd.!
                        //Pri odhlaseni updateovat vsetky subory
                        this.school.addStudent(newStudent);
                        System.out.println("Študent úspešne pridaný");
                        this.pauza(1);
                        this.menuNav("admin");
                        break;
                    case 2:
                        this.menuNav("heslo");
                        break;
                    case 3:
                        this.menuNav("vyhladavanie");
                        break;
                    case 4:
                        this.clearTerminal();
                        this.school.printFaculties();
                        System.out.println("=================================================");
                        System.out.println("[1] Exportuj zoznam");
                        System.out.println("[0] Späť ");
                        vyber = this.input.nextInt();
                        if (vyber == 0) {
                            this.menuNav("admin");
                        } else if (vyber == 1) {
                            //this.school.exportujFakulty();
                            this.pauza(1);
                            this.menuNav("admin");
                        }
                        break;
                    case 5:
                        this.clearTerminal();
                        this.school.printFields();
                        System.out.println("=================================================");
                        System.out.println("[1] Filtrovať podľa fakulty");
                        System.out.println("[2] Exportovat zoznam");
                        System.out.println("[0] Späť");
                        vyber = this.input.nextInt();
                        if (vyber == 0) {
                            this.menuNav("admin");
                        } else if (vyber == 1) {
                            this.school.printFaculties();
                            System.out.println("Zadaj ID fakulty na filtrovanie: ");
                            String id = this.input.next();
                            Faculty filter = this.finder.searchFaculties(id, this.school.getFacultyList());
                            this.clearTerminal();
                            System.out.println(filter.getName() + " - zoznam odborov");
                            filter.printFieldsOfStudy();
                            System.out.println("[1] Exportuj zoznam");
                            System.out.println("[0] Späť ");
                            vyber = this.input.nextInt();
                            if (vyber == 1) {
                                //filter.exportujOdbory();
                                this.pauza(1);
                                this.menuNav("admin");
                            } else if (vyber == 0) {
                                this.menuNav("admin");
                            }
                        } else if (vyber == 2) {
                            //this.school.exportujOdbory();
                            this.pauza(1);
                            this.menuNav("admin");
                        }
                        break;
                    case 6:
                        this.clearTerminal();
                        this.school.printGroups();
                        System.out.println("=================================================");
                        System.out.println("[1] Filtrovat podla odboru");
                        System.out.println("[2] Exportovat zoznam");
                        System.out.println("[0] Späť ");
                        vyber = this.input.nextInt();
                        if (vyber == 0) {
                            this.menuNav("admin");
                        } else if (vyber == 1) {
                            this.school.printFields();
                            System.out.println("Zadaj ID odboru na filtrovanie: ");
                            String id = this.input.next();
                            FieldOfStudy filter = this.finder.searchFields(id, this.school.getFieldList());
                            this.clearTerminal();
                            System.out.println(filter.getName() + " - zoznam skupin");
                            filter.printGroups();
                            System.out.println("[1] Exportuj zoznam");
                            System.out.println("[0] Späť ");
                            vyber = this.input.nextInt();
                            if (vyber == 1) {
                                filter.exportGroups(filter.getGroups(), filter.getName() + "- zoznam skupin");
                                this.pauza(1);
                                this.menuNav("admin");
                            } else if (vyber == 0) {
                                this.menuNav("admin");
                            }
                        } else if (vyber == 2) {
                            this.school.exportGroups(this.school.getGroupList(), this.school.getName() + "- zoznam skupin");
                            this.pauza(1);
                            this.menuNav("admin");
                        }
                        break;
                    case 7:
                        this.clearTerminal();
                        this.school.printSubjects();
                        System.out.println("=================================================");
                        System.out.println("[1] Filtrovat podla odboru");
                        System.out.println("[2] Exportuj zoznam");
                        System.out.println("[0] Späť ");
                        vyber = this.input.nextInt();
                        if (vyber == 0) {
                            this.menuNav("admin");
                        } else if (vyber == 1) {
                            this.school.printFields();
                            System.out.println("Zadaj ID odboru na filtrovanie: ");
                            String id = this.input.next();
                            FieldOfStudy filter = this.finder.searchFields(id, this.school.getFieldList());
                            this.clearTerminal();
                            System.out.println(filter.getName() + " - zoznam predmetov");
                            filter.printSubjects();
                            System.out.println("[1] Exportuj zoznam");
                            System.out.println("[0] Späť ");
                            vyber = this.input.nextInt();
                            if (vyber == 1) {
                                filter.exportSubjects(filter.getSubjects(), filter.getName() + "- zoznam predmetov");
                                this.pauza(1);
                                this.menuNav("admin");
                            } else if (vyber == 0) {
                                this.menuNav("admin");
                            }
                        } else if (vyber == 2) {
                            this.school.exportSubjects(this.school.getSubjectList(), this.school.getName()
                                    + "- zoznam predmetov");
                            this.pauza(1);
                            this.menuNav("admin");
                        }
                        break;
                    case 8:
                        //TODO 1.zmenit cestu kde sa exportuju zoznamy
                        //TODO 2. pridat export rozdelenia skupin a predmetov odboru
                        ExportToFile e = new ExportToFile();
                        e.exportStudents(this.school.getStudentsList(), "ImportData/TestExport/Students");
                        e.exportSubjects(this.school.getSubjectList(), "ImportData/TestExport/Subjects");
                        e.exportAdmins(this.school.getAdminList(), "ImportData/TestExport/Admins");
                        e.exportGroups(this.school.getGroupList(), "ImportData/TestExport/Groups");
                        e.exportTeachers(this.school.getTeacherspList(), "ImportData/TestExport/Teachers");
                        e.exportFields(this.school.getFieldList(), "ImportData/TestExport/Fields");
                        e.exportFaculties(this.school.getFacultyList(), "ImportData/TestExport/Faculties");
                        e.exportStudentAssignment(this.school.getStudentsList(), "ImportData/TestExport/rozdelenie");
                        e.exportTeachersAssignment(this.school.getTeacherspList(), "ImportData/TestExport/rozdeleniePredmetov");
                        e.exportFieldsAssignment(this.school.getFacultyList(), "ImportData/TestExport/pridelenieOdborov");
                        e.exportGSAssignment(this.school.getFieldList(), "ImportData/TestExport/skupApred");
                        this.loggedUser = null;
                        this.menuNav("main");
                        break;
                }
                break;
            case "teacher":
                this.clearTerminal();
                this.loggedTeacher = (Teacher)this.loggedUser;
                this.loggedTeacher.toString();
                System.out.println("_________________________________________________");
                System.out.println("Menu:");
                System.out.println("[1] Moje Predmety");
                System.out.println("[2] Zmena Hesla");
                System.out.println("[3] Moji študenti");
                System.out.println("[4] Zapisať známku");
                System.out.println("[5] Odhlásiť");
                System.out.println("=================================================");
                System.out.println("Zadaj cislo želanej možnosti");
                vyber = this.input.nextInt();
                switch (vyber) {
                    case 1:
                        this.menuNav("");
                        break;
                    case 2:
                        this.menuNav("heslo");
                        break;
                    case 3:
                        this.menuNav("student-3");
                        break;
                    case 4:
                        loggedStudent.exportSubjects();
                        this.menuNav("student");
                        break;
                    case 5:
                        ExportToFile e = new ExportToFile();
                        e.exportStudents(this.school.getStudentsList(), "ImportData/TestExport/Students");
                        e.exportSubjects(this.school.getSubjectList(), "ImportData/TestExport/Subjects");
                        e.exportAdmins(this.school.getAdminList(), "ImportData/TestExport/Admins");
                        e.exportGroups(this.school.getGroupList(), "ImportData/TestExport/Groups");
                        e.exportTeachers(this.school.getTeacherspList(), "ImportData/TestExport/Teachers");
                        e.exportFields(this.school.getFieldList(), "ImportData/TestExport/Fields");
                        e.exportFaculties(this.school.getFacultyList(), "ImportData/TestExport/Faculties");
                        e.exportStudentAssignment(this.school.getStudentsList(), "ImportData/TestExport/rozdelenie");
                        e.exportTeachersAssignment(this.school.getTeacherspList(), "ImportData/TestExport/rozdeleniePredmetov");
                        e.exportFieldsAssignment(this.school.getFacultyList(), "ImportData/TestExport/pridelenieOdborov");
                        e.exportGSAssignment(this.school.getFieldList(), "ImportData/TestExport/skupApre");
                        loggedTeacher = null;
                        this.menuNav("main");
                        break;
                }
                break;
            case "student":
                this.clearTerminal();
                this.loggedStudent = (Student)this.loggedUser;
                this.loggedStudent.toString();
                System.out.println("_________________________________________________");
                System.out.println("Menu:");
                System.out.println("[1] Prihlásovanie predmetov");
                System.out.println("[2] Zmena Hesla");
                System.out.println("[3] Zobraziť predmety");
                System.out.println("[4] Exportovať predmety");
                System.out.println("[5] Odhlásiť");
                System.out.println("=================================================");
                System.out.println("Zadaj cislo želanej možnosti");
                vyber = this.input.nextInt();
                switch (vyber) {
                    case 1:
                        this.menuNav("student-1");
                        break;
                    case 2:
                        this.menuNav("heslo");
                        break;
                    case 3:
                        this.menuNav("student-3");
                        break;
                    case 4:
                        loggedStudent.exportSubjects();
                        this.menuNav("student");
                        break;
                    case 5:
                        //TODO 1.zmenit cestu kde sa exportuju zoznamy
                        //TODO 2. pridat export rozdelenia skupin a predmetov odboru DONE
                        ExportToFile e = new ExportToFile();
                        e.exportStudents(this.school.getStudentsList(), "ImportData/TestExport/Students");
                        e.exportSubjects(this.school.getSubjectList(), "ImportData/TestExport/Subjects");
                        e.exportAdmins(this.school.getAdminList(), "ImportData/TestExport/Admins");
                        e.exportGroups(this.school.getGroupList(), "ImportData/TestExport/Groups");
                        e.exportTeachers(this.school.getTeacherspList(), "ImportData/TestExport/Teachers");
                        e.exportFields(this.school.getFieldList(), "ImportData/TestExport/Fields");
                        e.exportFaculties(this.school.getFacultyList(), "ImportData/TestExport/Faculties");
                        e.exportStudentAssignment(this.school.getStudentsList(), "ImportData/TestExport/rozdelenie");
                        e.exportTeachersAssignment(this.school.getTeacherspList(), "ImportData/TestExport/rozdeleniePredmetov");
                        e.exportFieldsAssignment(this.school.getFacultyList(), "ImportData/TestExport/pridelenieOdborov");
                        e.exportGSAssignment(this.school.getFieldList(), "ImportData/TestExport/skupApre");
                        loggedStudent = null;
                        this.menuNav("main");
                        break;
                }
                break;
            case "student-1":
                this.clearTerminal();
                ArrayList<Subject> availableSubjects = new ArrayList<Subject>();
                HashMap<String, Subject> fieldSubjects = this.loggedStudent.getField().getSubjects();
                Iterator it = fieldSubjects.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    if (!this.loggedStudent.getSubjects().containsValue(pair.getValue())) {
                        availableSubjects.add((Subject)pair.getValue());
                    }
                }

                int i = 1;
                if (availableSubjects.size() != 0) {
                    for (Subject aSubject : availableSubjects) {
                        System.out.println("[" + i + "]" + "ID: " + aSubject.getId() + " " + aSubject.getName() + " " + aSubject.getFilledCapacity() + "/"
                                + aSubject.getCapacity());
                        i++;
                    }
                } else {
                    System.out.println("Nie sú dostupné žiadne predmety na zapísanie");
                }
                System.out.println("=============================================");
                System.out.println("Zadaj id predmetu na ktorý sa chceš zapísať alebo zadaj 0 pre návrat späť: ");
                String id = this.input.next();
                if (!id.equals("0")) {
                    fieldSubjects = this.loggedStudent.getField().getSubjects();
                    if ((Subject)fieldSubjects.get(id) != null && fieldSubjects.get(id).getFilledCapacity()
                            < fieldSubjects.get(id).getCapacity()) {
                        this.loggedStudent.addSubject(fieldSubjects.get(id));
                    } else {
                        System.out.println("Kapacita tohto predmetu bola naplnená.");
                        this.pauza(1);
                    }
                    this.menuNav("student-1");
                } else {
                    this.menuNav("student");
                }
                break;
            case "heslo":
                this.clearTerminal();
                String zadaneHeslo = null;
                String kontrolneHeslo = null;
                String noveHeslo = null;
                System.out.println("Zadaj nové heslo: ");
                zadaneHeslo = this.input.next();
                System.out.println("Potvrd nove heslo opätovným zadaním: ");
                kontrolneHeslo = this.input.next();
                if (zadaneHeslo.equals(kontrolneHeslo)) {
                    this.loggedUser.setPassword(zadaneHeslo);
                    if (this.loggedUser instanceof Student) {
                        this.menuNav("student");
                    } else if (this.loggedUser instanceof Admin) {
                        this.menuNav("admin");
                    } else if (this.loggedUser instanceof Teacher) {
                        this.menuNav("teacher");
                    }
                }
               /* if (this.loggedStudent != null) {
                    if (zadaneHeslo.equals(kontrolneHeslo)) {
                        noveHeslo = zadaneHeslo;
                        this.school.upravHeslo(this.loggedStudent, null, noveHeslo);
                        System.out.println("Heslo úspešne zmenené");
                        this.pauza(1);
                        this.menuNav("student");
                    } else {
                        System.out.println("Heslá sa nezhodujú");
                        this.pauza(1);
                        this.menuNav("student");
                    }
                } else {
                    if (zadaneHeslo.equals(kontrolneHeslo)) {
                        noveHeslo = zadaneHeslo;
                        this.school.upravHeslo(null, this.loggedAdmin, noveHeslo);
                        System.out.println("Heslo úspešne zmenené");
                        this.pauza(1);
                        this.menuNav("admin");
                    } else {
                        System.out.println("Heslá sa nezhodujú");
                        this.pauza(1);
                        this.menuNav("admin");
                    }
                }*/

                break;
            case "student-3":
                this.clearTerminal();
                System.out.println("Zoznam navštevovaných predmetov:");
                int poradoveCislo = 1;

                it = this.loggedStudent.getSubjects().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    Subject s = (Subject)pair.getValue();
                    System.out.println("[" + poradoveCislo + "]" + s.getId() + " " + s.getName());
                    poradoveCislo++;
                }
                System.out.println("Pre odhlásenie z predmetu zadaj ID predmetu");
                System.out.println("Pre návrat späť zadaj 0");
                String c = this.input.next();
                if (!(c.equals("0"))) {
                    this.loggedStudent.removeSubject(this.loggedStudent.getSubjects().get(c));
                    this.menuNav("student-3");
                }  else {
                    this.menuNav("student");
                }
                break;
            case "vyhladavanie":
                this.clearTerminal();
                System.out.println("Vyber kritérium hľadania: ");
                System.out.println("[1]Meno ");
                System.out.println("[2]Študijná skupina ");
                System.out.println("[3]Predmet ");
                System.out.println("[4]Odbor ");
                System.out.println("[5]Fakulta ");
                System.out.println("[0]Späť ");
                int moznost = this.input.nextInt();
                switch (moznost) {
                    case 0:
                        this.menuNav("admin");
                        break;
                    case 1:
                        int vstup = 0;
                        System.out.println("Meno hľadaného študenta: ");
                        String name = this.input.next();
                        System.out.println("Priezvisko hľadaného študenta: ");
                        String lastname = this.input.next();
                        this.clearTerminal();
                        Student hladanyStudent = this.finder.searchStudents(name, lastname, this.school.getStudentsList());
                        if (hladanyStudent != null) {
                            hladanyStudent.toString();
                            System.out.println("================================================");
                            System.out.println("[1] Exportovať zoznam");
                            System.out.println("[0] Späť");
                            vstup = this.input.nextInt();
                            if (vstup == 1) {
                                hladanyStudent.exportInfo();
                                this.pauza(1);
                                this.menuNav("vyhladavanie");
                            } else if (vstup == 0)  {
                                this.menuNav("vyhladavanie");
                            }
                        } else {
                            System.out.println("Zadané údaje nie sú správne");
                            this.pauza(1);
                            this.menuNav("vyhladavanie");
                        }
                        break;
                    case 2:
                        this.clearTerminal();
                        System.out.println("ID skupiny: ");
                        id = this.input.next();
                        this.clearTerminal();
                        Group hladanaSkupina = this.finder.searchGroup(id, this.school.getGroupList());
                        if (hladanaSkupina != null) {
                            System.out.println("Zoznam študentov skupiny: " + hladanaSkupina.getId());
                            hladanaSkupina.printStudents();
                            System.out.println("================================================");
                            System.out.println("[1] Exportovať zoznam");
                            System.out.println("[0] Späť");
                            vstup = this.input.nextInt();
                            if (vstup == 1) {
                                hladanaSkupina.exportStudents(hladanaSkupina.getStudentsList(), "Skupina " +
                                        hladanaSkupina.getName() + "- zoznam studentov ");
                                this.pauza(1);
                                this.menuNav("vyhladavanie");
                            } else if (vstup == 0)  {
                                this.menuNav("vyhladavanie");
                            }
                        } else {
                            System.out.println("Zadané údaje nie sú správne");
                            this.pauza(1);
                            this.menuNav("vyhladavanie");
                        }
                        break;
                    case 3:
                        this.clearTerminal();
                        System.out.println("Zadaj ID predmetu: ");
                        id = this.input.next();
                        this.clearTerminal();
                        Subject hladanyPredmet = this.finder.searchSubjects(id, this.school.getSubjectList());
                        if (hladanyPredmet != null) {
                            System.out.println("Zoznam študentov prihlásených na " + hladanyPredmet.getName());
                            hladanyPredmet.printStudents();
                            System.out.println("================================================");
                            System.out.println("[1] Exportovať zoznam");
                            System.out.println("[0] Späť");
                            vstup = this.input.nextInt();
                            if (vstup == 1) {
                                hladanyPredmet.exportStudents(hladanyPredmet.getStudentsList(), hladanyPredmet.getName() +
                                        "-zoznam studentov ");
                                this.pauza(1);
                                this.menuNav("vyhladavanie");
                            } else if (vstup == 0)  {
                                this.menuNav("vyhladavanie");
                            }
                        } else {
                            System.out.println("Zadané údaje nie sú správne");
                            this.pauza(1);
                            this.menuNav("vyhladavanie");
                        }
                        break;
                    case 4:
                        this.clearTerminal();
                        System.out.println("Zadaj ID odboru: ");
                        id = this.input.next();
                        this.clearTerminal();
                        FieldOfStudy hladanyOdbor = this.finder.searchFields(id, this.school.getFieldList());
                        if (hladanyOdbor != null) {
                            System.out.println("Zoznam študentov študujúcich odbor: " + hladanyOdbor.getName());
                            hladanyOdbor.printStudents();
                            System.out.println("================================================");
                            System.out.println("[1] Exportovať zoznam");
                            System.out.println("[0] Späť");
                            vstup = this.input.nextInt();
                            if (vstup == 1) {
                                hladanyOdbor.exportStudents(hladanyOdbor.getStudentsList(), hladanyOdbor.getName() +
                                        "- zoznam studentov");
                                this.pauza(1);
                                this.menuNav("vyhladavanie");
                            } else if (vstup == 0)  {
                                this.menuNav("vyhladavanie");
                            }
                        } else {
                            System.out.println("Zadané údaje nie sú správne");
                            this.pauza(1);
                            this.menuNav("vyhladavanie");
                        }
                        break;
                    case 5:
                        this.clearTerminal();
                        System.out.println("Zadaj ID Fakulty: ");
                        id = this.input.next();
                        this.clearTerminal();
                        Faculty hladanaFakulta = this.finder.searchFaculties(id, this.school.getFacultyList());
                        if (hladanaFakulta != null) {
                            System.out.println("Zoznam študentov fakulty: " + hladanaFakulta.getName());
                            hladanaFakulta.printStudents();
                            System.out.println("================================================");
                            System.out.println("[1] Exportovať zoznam");
                            System.out.println("[0] Späť");
                            vstup = this.input.nextInt();
                            if (vstup == 1) {
                                hladanaFakulta.exportStudents(hladanaFakulta.getStudentsList(), hladanaFakulta.getName() +
                                        "- zoznam studentov");
                                this.pauza(1);
                                this.menuNav("vyhladavanie");
                            } else if (vstup == 0)  {
                                this.menuNav("vyhladavanie");
                            }
                        } else {
                            System.out.println("Zadané údaje nie sú správne");
                            this.pauza(1);
                            this.menuNav("vyhladavanie");
                        }
                        break;
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    private  void clearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {

        }
        //System.out.print('\u000C');
    }

    private  void mainPage() {
        this.clearTerminal();
        System.out.println("*******************************");
        System.out.println("*      Študentský Systém      *");
        System.out.println("*                             *");
        System.out.println("*     Dostupný užívatelia     *");
        System.out.println("*     |       |        |      *");
        System.out.println("*   Admin   Teacher  Študent  *");
        System.out.println("*                             *");
        System.out.println("*******************************");
        //this.clearTerminal();
        this.menuNav("login");

    }

    private void pauza(int pocetSekund) {
        try {
            TimeUnit.SECONDS.sleep(pocetSekund);
        } catch (Exception e) {

        }
    }

    private void loginForm() {
        System.out.println("Prihlásenie Užívateľa");
        System.out.println("Login:");
        this.login = this.input.next();
        System.out.println("Heslo");
        this.pass = this.input.next();
        this.loggedUser = this.school.loginAuthentification(this.login, this.pass);
        if (this.loggedUser != null) {
            if (this.loggedUser instanceof Admin) {
                this.loggedAdmin = (Admin)this.loggedUser;
                this.menuNav("admin");
            } else if (this.loggedUser instanceof Student) {
                this.loggedStudent = (Student)this.loggedUser;
                this.menuNav("student");
            } else if (this.loggedUser instanceof Teacher) {
                this.loggedTeacher = (Teacher)this.loggedUser;
                this.menuNav("teacher");
            }
        } else {
            System.out.println("Zlé prihlasovacie údaje");
            this.pauza(1);
            this.menuNav("login");
        }
    }
}
