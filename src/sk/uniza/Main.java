package sk.uniza;


import sk.uniza.fileHandling.ExportToFile;
import sk.uniza.school.Faculty;
import sk.uniza.school.School;

/**
 * Created by IntelliJ IDEA.
 * User: marek
 * Date: 4/3/2022
 * Time: 6:36 PM
 */
public class Main {

    public static void main(String[] args) {
        //TODO Write your code
        UserInterface userInterface = new UserInterface();
        //School sk1 = new School("5", "Uniza");
        /*sk1.testLoad();
        Search s = new Search();
        Faculty f = s.searchFaculties("X4UQS2", sk1.getFacultyList());
        f.printFieldsOfStudy();
        /*
        sk1.loadStudents("Students.csv");
        sk1.printStudents();
        ExportToFile e = new ExportToFile();
        e.exportStudents(sk1.getStudentsList(), "ExportData/Students");
        //UserInterface userInterface = UserInterface.getInstance();
        sk1.loadFaculties("Faculties.csv");
        sk1.loadFields("Fields.csv");
        sk1.loadGroups("Groups.csv");
        sk1.loadAdmins("Admins.csv");
        sk1.loadSubjects("Subjects.csv");
        sk1.loadTeachers("Teachers.csv");

        sk1.assignFields();
        sk1.fieldGrpSubAssign();
        sk1.assignSubjects();

        sk1.assignSubjectsToTeachers();

        sk1.printGroups();
        sk1.printFields();
        sk1.printSubjects();
        sk1.printTeachers();
        sk1.printAdmins();
        sk1.printFaculties();
        e.exportSubjects(sk1.getSubjectList(), "ExportData/Predmety");
        e.exportAdmins(sk1.getAdminList(), "ExportData/Admini");
        e.exportFaculties(sk1.getFacultyList(), "ExportData/Fakulty");
        e.exportFields(sk1.getFieldList(), "ExportData/Odbory");
        e.exportTeachers(sk1.getTeacherspList(), "ExportData/Ucitelia");
        e.exportGroups(sk1.getGroupList(), "ExportData/Skupiny");*/
        //UserInterface userInterface = new UserInterface();
        //ExportToFile e = new ExportToFile();
        //e.exportStudentAssignment(sk1.getStudentsList(), "studenti");
        //e.exportTeachersAssignment(sk1.getTeacherspList(), "ucitelia");
        //e.exportFieldsAssignment(sk1.getFacultyList(), "fakultyAodbory");
        //e.exportGSAssignment(sk1.getFieldList(), "subAG");
    }
}
