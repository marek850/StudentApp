package sk.uniza.generate;

import sk.uniza.school.Faculty;
import sk.uniza.school.FieldOfStudy;
import sk.uniza.school.Group;

import java.util.HashMap;
import java.util.Random;

/**
 * 4/3/2022 - 6:36 PM
 * Trieda sluzi na generovanie nahodnych retazcov a instancii tried z zoznamov
 * @author marek
 */
public class Generate {
    public Generate() {
    }

    /**
     * Vytvara nahodny alfanumericky retazec s dlzkou zadanou parametrom
     * @param size dlzka nahodneho retazca
     * @return alfanumericky string
     */
    public String generateString(int size) { //Funkcionalita vytvarania nahodneho alfanumerickeho stringu prevzata z https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
        String stringChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int index = (int)(stringChars.length() * Math.random());
            sb.append(stringChars.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Vytvara a vracia nahodny retazec zlozeny z cisel a velkych pismen abecedy s velkostou zadanou vstupnym parametrom
     * @param size dlzka retazca
     * @return nahodny retazec zlozeny z cisel a velkych pismen
     */
    public String generateID(int size) {
        String stringChars = "0123456789" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int index = (int)(stringChars.length() * Math.random());
            sb.append(stringChars.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Vracia nahodne vybratu skupinu  z zoznamu skupin urceneho vstupnym parametrom
     * @param groups zoznam skupin
     * @return nahodne zvolena Skupina
     */
    public Group generateGroup(HashMap<String, Group> groups) {
        Object randomKey = groups.keySet().toArray()[new Random().nextInt(groups.keySet().toArray().length)];
        return groups.get(randomKey);
    }
    /**
     * Vracia nahodne vybratu fakultu  z zoznamu fakult urceneho vstupnym parametrom
     * @param faculties zoznam fakult
     * @return nahodne zvolena Fakulta
     */
    public Faculty generateFaculty(HashMap<String, Faculty> faculties) {
        Object randomKey = faculties.keySet().toArray()[new Random().nextInt(faculties.keySet().toArray().length)];
        return faculties.get(randomKey);
    }
    /**
     * Vracia nahodne vybraty odbor  z zoznamu odborov urceneho vstupnym parametrom
     * @param fieldOfStudy zoznam odborov
     * @return nahodne zvoleny Odbor
     */
    public FieldOfStudy generateFields(HashMap<String, FieldOfStudy> fieldOfStudy) {
        Object randomKey = fieldOfStudy.keySet().toArray()[new Random().nextInt(fieldOfStudy.keySet().toArray().length)];
        return fieldOfStudy.get(randomKey);
    }

}
